package com.example.feature.auth.login

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {

    val manager = LoginManager()

    routing {
        get("/login") {
            manager.signIn(call = call)
        }
    }
}