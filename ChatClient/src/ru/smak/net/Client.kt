package ru.smak.net

import kotlinx.coroutines.*
import java.net.Socket

class Client(
    host: String,
    port: Int,
) {
    private val s: Socket
    private val cmn: Communicator
    private val mainCoroutineScope = CoroutineScope(Dispatchers.IO + Job())

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
            while (isActive) {
                val s = readlnOrNull() ?: ""
                cmn.sendData(s)
            }
        }
    }

    private fun parse(data: String) {
        data.split(":", limit = 2).let {
            when (it[0]) {
                "INTR" -> {
                    print("Представьте себя: ")
                }

                "REINTR" -> {
                    print("Имя занято, выберите другое: ")
                }

                "NAMEOK" -> {
                    println("Вы успешно вошли в чат")
                }

                "MSG" -> {
                    println(it[1])
                }

                "NEW" -> {
                    println("Пользователь ${it[1]} вошёл в чат")
                }
                "EXIT" -> {
                    println("Пользователь ${it[1]} покинул чат")
                }
            }
        }
    }
}