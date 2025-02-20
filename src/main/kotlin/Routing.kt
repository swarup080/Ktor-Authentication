package com.example

import com.example.repository.UserRepository
import com.example.repository.UserRepositoryImplementation
import com.example.service.CreateUserParams
import com.example.service.UserService
import com.example.service.UserServiceImplementation
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/register") {
            val service: UserService = UserServiceImplementation()
            val repository: UserRepository = UserRepositoryImplementation(service)
            val params = call.receive<CreateUserParams>()
            val result = repository.registerUser(params)
            call.respond(result)
        }
    }
}


