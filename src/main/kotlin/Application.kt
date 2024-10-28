import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import plugins.configureSerialization
import repository.UserRepository
import routing.configureRouting
import service.UserService

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    val repository = UserRepository()
    val service = UserService(repository)

    configureSerialization()
    configureRouting(service)
}
