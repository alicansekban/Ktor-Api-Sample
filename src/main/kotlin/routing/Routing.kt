package routing

import io.ktor.server.application.Application
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import service.UserService

fun Application.configureRouting(userService: UserService) {
    routing {
        route("/api/user") {
            userRoute(userService)
        }
    }
}