# Example of REST Microservice with Javalin

Playing around `Kotlin` and `Javalin` to create a simple CRUD REST API following [this tutorial](https://javalin.io/tutorials/simple-kotlin-example)


## Running the application
### Basic Kotlin `hello world`
Run `main` function from file [Main.kt](./src/main/kotlin/Main.kt)
```bash
curl --location 'http://localhost:7070/'
```

### REST API with Javalin
Run `main` function from file [HelloWorld.kt](./src/main/kotlin/HelloWorld.kt)

You should be able to see the [service endpoint](http://localhost:7070/) in the `run` terminal.

#### Available requests
```bash
curl --location 'http://localhost:7070/'
```
```bash
curl --location 'http://localhost:7070/hello'
```
```bash
curl --location 'http://localhost:7070/users/'
```
```bash
curl --location 'http://localhost:7070/users/ages'
```
```bash
curl --location 'http://localhost:7070/users/0'
```
```bash
curl --location 'http://localhost:7070/users/4'
```

### CRUD REST API with Javalin
Run `main` function from file [app/Main.kt](src/main/kotlin/app/Main.kt)

You should be able to see the [service endpoint](http://localhost:7070/) in the `run` terminal.

#### Available requests
```bash
curl --location 'http://localhost:7070/'
```
```bash
curl --location 'http://localhost:7070/users/'
```
```bash
curl --location 'http://localhost:7070/users/0'
```
```bash
curl --location 'http://localhost:7070/users/email/bob@bob.kt'
```
```bash
curl --location 'http://localhost:7070/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "New Bob",
    "email": "newbob@bob.kt"
}'
```
```bash
curl --location 'http://localhost:7070/users/new' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "New new Bob",
    "email": "newnewbob@bob.kt"
}'
```
```bash
curl --location --request PATCH 'http://localhost:7070/users/4' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "New Bob patched",
    "email": "newbob@bob.kt"
}'
```
```bash
curl --location --request PATCH 'http://localhost:7070/users/patch/4' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "New Bob patched again",
    "email": "newbob@bob.kt"
}'
```
```bash
curl --location --request DELETE 'http://localhost:7070/users/4'
```

## Resources
 - https://javalin.io/tutorials/
 - https://javalin.io/tutorials/gradle-setup
 - https://javalin.io/tutorials/simple-kotlin-example
 - https://javalin.io/documentation#getting-started
 - [Kotlin for server side](https://kotlinlang.org/docs/server-overview.html#0)
 - https://www.baeldung.com/javalin-rest-microservices
 - https://www.toptal.com/kotlin/kotlin-server-side-development
 - https://kotlinlang.org/docs/object-declarations.html#companion-objects
 - https://www.jetbrains.com/help/idea/content-roots.html#folder-categories

