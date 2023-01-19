package com.example

import com.example.feature.auth.configureAuthRouting
import com.example.feature.auth.login.LoginManager
import com.example.feature.comments.configureCommentsRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import configureNewsRouting
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.cors.*
import io.ktor.server.plugins.cors.CORS
import io.ktor.server.plugins.cors.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.createMissingTablesAndColumns
import kotlin.math.log

fun main() {
    Database.connect(
        "jdbc:postgresql://db.qvtryvgkycvythghwuwn.supabase.co:5432/postgres?user=postgres&password=WXPAtmouXCKvltDA",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "WXPAtmouXCKvltDA"
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

    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Delete)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        anyHost()
    }

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
