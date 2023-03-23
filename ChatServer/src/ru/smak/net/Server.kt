package ru.smak.net

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.ServerSocket

/**
 * Класс сервера для поддержки работы чата
 * @param port порт, который прослушивает сервер
 */
class Server(
    val port: Int = 5004,
) {
    private val ss = ServerSocket(port)
    private val mainCoroutineScope = CoroutineScope(Dispatchers.IO + Job())

    /**
     * Метод выполняет запуск сервера
     */
    fun start() = mainCoroutineScope.launch {
        while (isActive) {
            try {
                ss.accept().apply {
                    ConnectedClient(this).apply {
                        launch {
                            try {
                                start()
                            } catch (e: Throwable){
                                // TODO: Ошибка в подключенном клиенте
                            }
                        }
                    }
                }
            } catch (_: Throwable){
            }
        }
    }

    fun stop(){
        mainCoroutineScope.cancel("Пользователь завершил работу сервера")
        ss.close()
    }
}