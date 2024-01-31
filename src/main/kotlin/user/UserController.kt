package user

import java.util.*
import io.javalin.http.Context

class UserController {
    companion object Handler {

        fun fetchAllUsernames(ctx: Context) {
            val dao = UserDao.instance()
            val allUsers: Iterable<String> = dao.getAllUserNames()
            ctx.json(allUsers)
        }

        fun fetchAllUserAges(ctx: Context) {
            val dao = UserDao.instance()
            val allUsers: Iterable<Int> = dao.getAllUserAges()
            ctx.json(allUsers)
        }

        fun fetchById(ctx: Context) {
            val id: Int = Objects.requireNonNull(ctx.pathParam("id")).toInt()
            val dao = UserDao.instance()
            val user: Optional<User> = dao.getUserById(id)
            if (user.isPresent) {
                ctx.json(user.get())
            } else {
                ctx.html("Not Found")
            }
        }
    }
}