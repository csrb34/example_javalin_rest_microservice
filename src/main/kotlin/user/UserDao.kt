package user

import java.util.*
import java.util.stream.Collectors


class UserDao {
    companion object {
        fun instance(): UserDao = UserDao()
    }

    private val users = listOf(
        User(0, "Steve Rogers", 25),
        User(1, "Tony Stark", 12),
        User(2, "Carol Danvers", 37),
        User(3, "Maria Fernandez", 32)
    )

    fun getUserById(id: Int): Optional<User> {
        return users.stream()
            .filter { (currentId): User -> currentId == id }
            .findAny()
    }

    fun getAllUserNames(): Iterable<String> {
        return users.stream()
            .map { (_, name): User -> name }
            .collect(Collectors.toList())
    }

    fun getAllUserAges(): Iterable<Int> {
        return users.stream()
            .map { (_, _, age): User -> age }
            .collect(Collectors.toList())
    }
}