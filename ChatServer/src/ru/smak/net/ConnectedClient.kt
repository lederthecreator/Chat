package ru.smak.net

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.smak.net.Communicator
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

    var name: String? = null
        private set(value) {
            value?.let { vl ->
                if (list.find { it.name == value } == null) {
                    field = vl
                    sendToAllConnectedClients({ if (it == this) "NAMEOK" else "NEW" },
                        { if (it != this) vl else "" }
                    )
                } else cmn.sendData("REINTR:")
            } ?: cmn.sendData("REINTR:")
        }

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
                    name?.let {
                        sendToAllConnectedClients({ "EXIT" }, { _ -> it })
                    }
                }
            }
            launch {
                cmn.sendData("INTR:")
            }
        }
    }

    fun parse(data: String) {
        if (name != null) sendToAllConnectedClients({ "MSG" }, { "${if (it == this) "Вы" else name}: $data" })
        else name = data
    }


    private fun sendToAllConnectedClients(cmd: (ConnectedClient) -> String, data: (ConnectedClient) -> String) {
        _list.forEach {
            it.name?.let {_->
                it.cmn.sendData("${cmd(it)}:${data(it)}");
            }
        }
    }
}