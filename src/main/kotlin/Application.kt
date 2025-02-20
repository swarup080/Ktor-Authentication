package com.example

import com.example.database.DatabaseFactory
import com.example.repository.UserRepository
import com.example.repository.UserRepositoryImplementation
import com.example.route.authRoutes
import com.example.security.configureSecurity
import com.example.service.UserService
import com.example.service.UserServiceImplementation
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()
    configureSecurity()
    configureSerialization()
    configureRouting()
}
