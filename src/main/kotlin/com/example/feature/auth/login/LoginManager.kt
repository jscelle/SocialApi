package com.example.feature.auth.login

import com.example.database.users.Users
import com.example.feature.auth.AuthOutput
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class LoginManager {
    suspend fun signIn(
        call: ApplicationCall
    ) {
        val user = call.receive<LoginInput>()

        Users.getUser(user.login)?.let {
            if (it.password == user.password) {
                call.respond(
                    AuthOutput(
                        login = it.login,
                        password = it.password,
                        id = it.id,
                        isAdmin = it.isAdmin
                    )
                )
                return
            }
        }
        call.respond(HttpStatusCode.Conflict, "Wrong cred's!")
    }

    fun signed(
        login: String,
        password: String
    ): Boolean {
        Users.getUser(login = login)?.let {
            return it.password == password
        }
        return false
    }

    fun isAdmin(
        login: String,
        password: String
    ): Boolean {
        Users.getUser(login = login)?.let {
            return it.password == password && it.isAdmin
        }
        return false
    }
}