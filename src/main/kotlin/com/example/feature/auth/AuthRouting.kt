package com.example.feature.auth

import com.example.feature.auth.login.configureLoginRouting
import com.example.feature.auth.register.configureRegisterRouting
import io.ktor.server.application.*

fun Application.configureAuthRouting() {
    configureLoginRouting()
    configureRegisterRouting()
}