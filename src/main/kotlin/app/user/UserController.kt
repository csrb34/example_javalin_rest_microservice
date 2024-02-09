package app.user

import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import io.javalin.http.bodyAsClass
import io.javalin.http.pathParamAsClass

class UserController {
    companion object {
        private val userDao = UserDao()

        fun create (ctx: Context, sendUserBack: Boolean = false): Context {
            val user = ctx.bodyAsClass<User>()
            return if (sendUserBack) {
                val newUser = userDao.save(name = user.name, email = user.email, true)
                ctx.status(200)
                ctx.json(newUser)
            } else {
                userDao.save(name = user.name, email = user.email)
                ctx.status(201)
            }
        }

        fun get (ctx: Context) {
            val userId = ctx.pathParamAsClass<Int>("user-id").get()
            val user = userDao.findById(userId) ?: throw NotFoundResponse()
            ctx.json(user)
        }

        fun getAll (ctx: Context) {
            ctx.json(userDao.users)
        }

        fun delete (ctx: Context) {
            val userId = ctx.pathParamAsClass<Int>("user-id").get()
            userDao.delete(userId) ?: throw NotFoundResponse()
            ctx.status(204)
        }
    }
}