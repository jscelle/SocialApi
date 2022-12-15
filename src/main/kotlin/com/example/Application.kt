package com.example

import com.example.feature.auth.configureAuthRouting
import com.example.feature.auth.login.LoginManager
import com.example.feature.comments.configureCommentsRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import configureNewsRouting
import io.ktor.server.auth.*
import org.jetbrains.exposed.sql.Database
import kotlin.math.log

fun main() {
    Database.connect("jdbc:postgresql://localhost:5555/web", driver = "org.postgresql.Driver",
        user = "postgres", password = "himgaf-2Gozzi-hyhtur"
    )

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

}

fun Application.module() {
    configureSerialization()
    configureAuthRouting()
    configureRouting()

    configureNewsRouting()

    configureCommentsRouting()

    install(Authentication) {
        val manager = LoginManager()
        basic("auth-basic") {
            realm = "Logged user access"
            validate { credentials ->
                if (manager.signed(login = credentials.name, password = credentials.password)) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }

        basic("admin") {
            realm = "Admin access only"
            validate { credentials ->
                if (manager.isAdmin(login = credentials.name, password = credentials.password)) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
}
