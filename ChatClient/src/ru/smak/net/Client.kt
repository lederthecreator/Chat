package ru.smak.net

import com.google.gson.Gson
import kotlinx.coroutines.*
import ru.leder.gui.MainWindow
import ru.leder.gui.dto.Dto
import ru.leder.gui.enums.MessageType
import java.net.Socket

class Client(
    host: String,
    port: Int,
) {
    private val s: Socket
    private val cmn: Communicator
    private val mainCoroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val gson = Gson()
    private val mainWindow = MainWindow { sendDtoRequest(it) }

    init {
        s = Socket(host, port)
        cmn = Communicator(s)
    }

    fun start() = mainCoroutineScope.launch {
        launch {
            cmn.startReceiving {
                parse(it)
            }
        }

        launch {
//            while (isActive) {
//                val s = readlnOrNull() ?: ""
//                sendRequest(s)
//            }
            mainWindow.isVisible = true
        }
    }

    private fun sendDtoRequest(input: Dto) {
        val jsonDto = gson.toJson(input)

        cmn.sendData(jsonDto)
    }

    private fun sendRequest(input: String) {
        if (input == "") {
            return
        }

        val request = parseInputString(input) ?: throw Exception("Cannot parse input")

        val jsonRequest = gson.toJson(request)

        cmn.sendData(jsonRequest)
    }

    private fun parseInputString(input: String) : Request? {
        val splitInput = input.split(" ", limit = 2)

        val operation = splitInput[0]
        val args = if (splitInput.size > 1) splitInput[1] else ""
        val splitArgs = args.split('|')

        when (operation) {
            "LOGIN" -> {
                return Request(
                    operation = operation,
                    data = object {
                        val login = splitArgs[0]
                        val password = splitArgs[1]
                    }
                )
            }

            "SIGNUP" -> {
                return Request (
                    operation = operation,
                    data = object {
                        val login = splitArgs[0]
                        val password = splitArgs[1]
                    }
                )
            }

            "MESSAGE" -> {
                return Request (
                    operation = operation,
                    data = splitArgs[0]
                )
            }
        }

        return null
    }

    private fun parse(json: String) {
        val response = Utils.getMapFromJson(json)

        val success = response["success"].toString().toBoolean()
        val operation = response["operation"].toString()
        val data = response["data"].toString()

        if (!success) {
            //println("Во время операции {$operation} возникла ошибка: $data \n")
            if (operation == "MESSAGE") {
                mainWindow.messageReceiver(MessageType.ErrorMessage, data)
                return
            }
            mainWindow.errorReceiver(operation, data)
            return
        }

        when (operation) {
            "LOGIN" -> {
                // println("Hello, $data")
                mainWindow.loginReceiver(data)
            }

            "SIGNUP" -> {
                // println("Hello, $data")
                mainWindow.signUpReceiver(data)
            }

            "MESSAGE" -> {
                // println(data)
                mainWindow.messageReceiver(MessageType.UserMessage, data)
            }

            "EXIT" -> {
                // println("Пользователь ${data} вышел из чата. Надеемся, навсегда.")
                mainWindow.messageReceiver(MessageType.SystemMessage, "Пользователь ${data} вышел из чата. Надеемся, навсегда.")
            }

            "USERLOGGED" -> {
                // println(data)
                mainWindow.messageReceiver(MessageType.SystemMessage, data)
            }

            "USERSIGNIN" -> {
                // println(data)
                mainWindow.messageReceiver(MessageType.SystemMessage, data)
            }
        }
    }
}