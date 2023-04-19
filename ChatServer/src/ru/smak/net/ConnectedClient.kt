package ru.smak.net

import com.google.gson.Gson
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import ru.smak.net.entities.ChatUser
import ru.smak.net.entities.ChatUsers
import java.net.Socket

/**
 * Организация работы с подключенным клиентом на стороне сервера
 * @param socket сокет подключенного клиента
 */
class ConnectedClient(
    private val socket: Socket,
) {
    companion object {
        private val _list = mutableListOf<ConnectedClient>()
        val list: List<ConnectedClient>
            get() = _list.toList()
    }

    private val cmn = Communicator(socket)
    private val gson = Gson()

    private var userLogin: String? = null

    init {
        _list.add(this)
    }

    suspend fun start() {
        coroutineScope {
            launch {
                try {
                    cmn.startReceiving { parse(it) }
                } catch (e: Throwable) {
                    _list.remove(this@ConnectedClient)
                    userLogin?.let {
                        sendToAllConnectedClients({ "EXIT" }, { "${if (it.userLogin == this@ConnectedClient.userLogin) "Вы" else userLogin}" })
                    }
                }
            }
        }
    }

    private fun parse(data: String) {
        val request = Utils.getMapFromJson(data)

        when (request["operation"]) {
            "LOGIN" ->  processLogin(request)

            "SIGNUP" -> processSignUp(request)

            "MESSAGE" -> processMessage(request)
        }
    }

    private fun processLogin(request: Map<String, Any>) {
        if (!userLogin.isNullOrEmpty()) {
            sendErrorResponse("LOGIN", "Вы уже вошли в аккаунт '$userLogin'")
            return
        }

        val authData = request["data"]

        if (authData == null) {
            sendErrorResponse("LOGIN", "Не найдены данные для авторизации")
            return
        }

        val jsonAuthData = gson.toJson(authData)
        val parsedAuthData = Utils.getMapFromJson(jsonAuthData)
        val login = parsedAuthData["login"].toString()
        val password = parsedAuthData["password"]

        if (login.isEmpty()) {
            sendErrorResponse("LOGIN", "Пустой логин")
            return
        }

        val logins = mutableListOf<String>()
        transaction {
            addLogger(StdOutSqlLogger)

            logins.addAll(ChatUser.all().map {
                it.login
            })
        }

        if (!logins.contains(login)) {
            sendErrorResponse("LOGIN", "Не найден пользователь с логином '$login'")
            return
        }

        var userEntity : ChatUser? = null
        transaction {
            addLogger(StdOutSqlLogger)

            userEntity = ChatUser.find {
                ChatUsers.login eq login
            }.maxByOrNull { it.id }
        }

        if (userEntity!!.password == password) {
            userLogin = login
            val response = Response (
                success = true,
                operation = "LOGIN",
                data = login
            )
            val jsonResponse = gson.toJson(response)

            cmn.sendData(jsonResponse)
            sendToAllConnectedClients( {"USERLOGGED"}, {
                if (it.userLogin != this.userLogin)
                    "Пользователь $userLogin зашел в чат! Жаль."
                else {
                    "Вы успешно вошли в чат!"
                }
            } )
            return
        }

        sendErrorResponse("LOGIN", "Неверный пароль")
    }

    private fun processSignUp(request: Map<String, Any>) {
        val signUpData = request["data"]
        if (signUpData == null) {
            sendErrorResponse("SIGNUP", "Не найдены данные для регистрации")
            return
        }

        val jsonSignUpData = gson.toJson(signUpData)
        val parsedData = Utils.getMapFromJson(jsonSignUpData)
        val parsedLogin = parsedData["login"].toString()
        val parsedPassword = parsedData["password"].toString()

        if (parsedLogin.isEmpty()) {
            sendErrorResponse("SIGNUP", "Логин не может быть пустым")
            return
        }

        val logins = mutableListOf<String>()
        transaction {
            logins.addAll(ChatUser.all().map {
                it.login
            })
        }

        if (logins.contains(parsedLogin)) {
            sendErrorResponse("SIGNUP", "Пользователь с логином $parsedLogin уже зарегистрирован")
            return
        }

        var regUser : ChatUser? = null
        transaction {
            addLogger(StdOutSqlLogger)

            regUser = ChatUser.new {
                login = parsedLogin
                password = parsedPassword
            }
        }

        if (regUser == null) {
            sendErrorResponse("SIGNUP", "Ошибка при регистрации пользователя")
            return
        }

        userLogin = regUser!!.login
        val response = Response (
            success = true,
            operation = "SIGNUP",
            data = userLogin!!
        )
        val jsonResponse = gson.toJson(response)

        cmn.sendData(jsonResponse)
        sendToAllConnectedClients({ "USERSIGNIN" }, {
            if (it != this)
                "Пользователь $userLogin впервые в нашем чате! Пусть этот раз будет последним."
            else {
                "Вы впервые зашли в чат. Добро пожаловать!"
            }
        })
    }

    private fun processMessage(request: Map<String, Any>) {
        if (userLogin == null) {
            sendErrorResponse("MESSAGE", "Вы не вошли в чат")
            return
        }

        val messageData = request["data"]

        if (messageData.toString().isEmpty()) {
            sendErrorResponse("MESSAGE", "Сообщение не может быть пустым")
            return
        }

        val formattedMessage = messageData.toString().trim()

        sendToAllConnectedClients({ "MESSAGE" }, { "${if (it.userLogin == this.userLogin) "Вы" else userLogin}: $formattedMessage" })
    }


    private fun sendToAllConnectedClients(cmd: (ConnectedClient) -> String, data: (ConnectedClient) -> String) {
        _list.forEach {
            it.userLogin?.let { _->
                val response = Response(
                    success = true,
                    operation = cmd(it),
                    data = data(it)
                )
                val jsonResponse = gson.toJson(response)

                it.cmn.sendData(jsonResponse)
            }
        }
    }

    private fun sendErrorResponse(operation: String, message: String) {
        val response = Response(
            success = false,
            operation = operation,
            data = message
        )
        val jsonResponse = gson.toJson(response)

        cmn.sendData(jsonResponse)
    }
}