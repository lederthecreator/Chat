import kotlinx.coroutines.runBlocking
import ru.smak.net.Client

fun main() = runBlocking{
    Client("localhost", 5004).start().join()
}