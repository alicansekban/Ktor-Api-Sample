package routing

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import model.User
import routing.request.UserRequest
import routing.response.UserResponse
import service.UserService
import java.util.UUID

fun Route.userRoute(
    userService: UserService
) {
    post {
        val userRequest = call.receive<UserRequest>()
        val createdUser = userService.save(
            user = userRequest.toModel()
        ) ?: return@post call.respond(HttpStatusCode.BadRequest)

        call.response.header(
            name = "id",
            value = createdUser.id.toString()
        )
        call.respond(
            message = HttpStatusCode.Created
        )
    }

    get {
        val users = userService.findAll()
        call.respond(
            message = users.map { it.toResponse() }
        )
    }

    get(path = "/{id}") {
        val id : String = call.parameters["id"]
            ?: return@get call.respond(HttpStatusCode.BadRequest)

        val foundUser = userService.findById(id)
            ?: return@get call.respond(HttpStatusCode.NotFound)

        call.respond(
            message = foundUser.toResponse()
        )
    }
}

private fun UserRequest.toModel(): User =
    User(
        id = UUID.randomUUID(),
        username = this.username,
        password = this.password
    )

private fun User.toResponse() : UserResponse =
    UserResponse(
        id= this.id,
        username = this.username
    )