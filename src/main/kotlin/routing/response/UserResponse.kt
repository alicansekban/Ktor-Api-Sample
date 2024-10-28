package routing.response

import java.util.UUID
import kotlinx.serialization.Serializable
import utils.UUIDSerializer

@Serializable
data class UserResponse(
    @Serializable(with = UUIDSerializer::class)
    val id : UUID,
    val username : String
)
