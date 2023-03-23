import kotlinx.coroutines.runBlocking
import ru.smak.net.Server

fun main() = runBlocking{
    Server().start().join()
}