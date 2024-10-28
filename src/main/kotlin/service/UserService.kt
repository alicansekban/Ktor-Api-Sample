package service

import model.User
import repository.UserRepository
import java.util.UUID

class UserService(
    private val userRepository: UserRepository
) {

    fun findAll() : List<User> =
        userRepository.findAll()

    fun findById(id: String) : User? =
        userRepository.findById(
            id = UUID.fromString(id)
        )
    fun findByUsername(username : String) : User? =
        userRepository.findByUserName(username)
    fun save(user: User) : User? {
        val foundUser = findByUsername(user.username)
        return if (foundUser == null) {
            userRepository.save(user)
            user
        } else null
    }
}