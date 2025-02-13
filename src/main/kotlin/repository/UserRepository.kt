package repository

import model.User
import java.util.UUID

class UserRepository {
    private val users = mutableListOf<User>()

    fun findAll(): List<User> = users
    fun findById(id: UUID): User? = users.firstOrNull { it.id == id }
    fun findByUserName(userName: String): User? = users.firstOrNull { it.username == userName }
    fun save(user: User) : Boolean = users.add(user)
}