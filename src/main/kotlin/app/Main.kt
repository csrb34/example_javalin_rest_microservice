package app

import app.user.User
import app.user.UserDao
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.Javalin
import io.javalin.http.HttpStatus
import io.javalin.http.NotFoundResponse
import io.javalin.http.bodyAsClass
import io.javalin.http.pathParamAsClass

fun main() {
//    val app = Javalin.create().start(7070)
//    app.get("/") { ctx -> ctx.result("Hello World") }

    val userDao = UserDao()

    val app = Javalin.create {
        it.router.apiBuilder {

            get("/") { it.redirect("/users") } // redirect root to /users

            get("/users") { ctx ->
                ctx.json(userDao.users)
            }

            get("/users/{user-id}") { ctx ->
                val userId = ctx.pathParamAsClass<Int>("user-id").get()
                val user = userDao.findById(userId) ?: throw NotFoundResponse()
                ctx.json(user)
            }

            get("/users/email/{email}") { ctx ->
                val email = ctx.pathParam("email")
                val user = userDao.findByEmail(email) ?: throw NotFoundResponse()
                ctx.json(user)
            }

            post("/users") { ctx ->
//                val user = ctx.body()
//                println(user)
                val user = ctx.bodyAsClass<User>()
                userDao.save(name = user.name, email = user.email)
                ctx.status(201)
            }

            post("/users/new") { ctx ->
                val user = ctx.bodyAsClass<User>()
                val newUser = userDao.save(name = user.name, email = user.email, true)
                ctx.status(201)
                ctx.json(newUser)
            }

            patch("/users/{user-id}") { ctx ->
                val userId = ctx.pathParamAsClass<Int>("user-id").get()
                val user = ctx.bodyAsClass<User>()
                userDao.update(id = userId, user = user)
//              204 response code does not carry a payload body.
//              200 response could contain a payload body
                ctx.status(204)

            }

            patch("/users/patch/{user-id}") { ctx ->
                val userId = ctx.pathParamAsClass<Int>("user-id").get()
                val user = ctx.bodyAsClass<User>()
                val updatedUser = userDao.update(id = userId, user = user, true)
//              204 response code does not carry a payload body.
//              200 response could contain a payload body
                ctx.status(200)
                ctx.json(updatedUser ?: throw NotFoundResponse())
            }

            delete("/users/{user-id}") { ctx ->
                val userId = ctx.pathParamAsClass<Int>("user-id").get()
                userDao.delete(userId)
                ctx.status(204)
            }
        }
    }.apply {
        exception(ClassCastException::class.java) { _, ctx -> ctx.json("invalid body") }
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        error(HttpStatus.NOT_FOUND) { ctx -> ctx.json("not found") }
    }.start(7070)
}