package app

//import org.junit.jupiter.api.Assertions.*
import app.user.User
import app.user.UserDao
import app.user.UserController
import io.javalin.http.Context
import io.javalin.http.bodyAsClass
import io.javalin.http.pathParamAsClass
import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserControllerTest {
    private val ctx: Context = mockk<Context>(relaxed = true)

    @Test
    fun `POST to create users gives 201 for valid username`() {
        every { ctx.bodyAsClass<User>() } returns User("Carlos", "carlos@gmail.com", 1)
        UserController.create(ctx)
        verify { ctx.status(201) }
        verify { ctx.json(any()) wasNot(called)  }
    }

    @Test
    fun `POST to create user gives 201 and returns user`() {
        every { ctx.bodyAsClass<User>() } returns User("Carlos", "carlos@gmail.com", 1)
        UserController.create(ctx, true)
        verify {
            ctx.status(200)
//            ctx.json(any())
            ctx.json(any<User>())
//            ctx.json(User("Carlos", "carlos@gmail.com", 5))

//            doesn't work :(
//            ctx.json(User("Carlos", "carlos@gmail.com", any<Int>()))
        }
    }

    @Test()
    fun `POST to create users throws for invalid request body`() {
        assertThrows<ClassCastException> {
            every { ctx.bodyAsClass<Any>() } returns Any()
            UserController.create(ctx)
        }
    }

    @Test
    fun `GET user`() {
        every { ctx.pathParamAsClass<Int>("user-id").get() } returns 1
        UserController.get(ctx)
        verify {
            ctx.json(User(name = "Bob", email = "bob@bob.kt", id = 1))
        }
    }

    @Test
    fun `GET all user`() {
        every { ctx.pathParamAsClass<Int>("user-id").get() } returns 1
        UserController.getAll(ctx)
        verify {
            ctx.json(any<HashMap<Int, User>>())
        }
    }

    @Test
    fun `DELETE to remove user gives 204 for valid ID`() {
        every { ctx.pathParamAsClass<Int>("user-id").get() } returns 1
        UserController.delete(ctx)
        verify { ctx.status(204) }
        verify { ctx.json(any()) wasNot(called)  }
    }
}