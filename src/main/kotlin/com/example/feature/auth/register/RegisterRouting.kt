package com.example.feature.auth.register

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureRegisterRouting() {
    val registerManager = RegisterManager()
    routing {
        get ("/register") {
            registerManager.registerUser(call = call)
        }
    }
}