import io.javalin.Javalin
import user.UserController

fun main() {
    val app = Javalin.create(/*config*/)
        .get("/") { ctx -> ctx.result("Hello World") }
        .start(7070)

    app.get("/hello") { ctx -> ctx.html("Hello, Javalin!") }

    app.get("/users") { ctx -> UserController.Handler.fetchAllUsernames(ctx) }
    app.get("/users/ages") { ctx -> UserController.Handler.fetchAllUserAges(ctx) }
    app.get("/users/{id}") { ctx -> UserController.Handler.fetchById(ctx) }
}
