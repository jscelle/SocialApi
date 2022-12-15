package com.example.feature.auth.register

import com.example.database.users.UserDTO
import com.example.database.users.Users
import com.example.feature.auth.AuthOutput
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class RegisterManager() {

    suspend fun registerUser(call: ApplicationCall) {
        val user = call.receive<RegisterInput>()

        val savedUser = Users.getUser(user.login)

        if (savedUser != null) {

            call.respond(HttpStatusCode.Conflict, "User allready registered!")

        } else {

            Users.insert(
                UserDTO(
                    id = 0,
                    login = user.login,
                    password = user.password,
                    isAdmin = user.isAdmin
                )
            )

            val newUser = Users.getUser(login = user.login) ?: return

            call.respond(
                AuthOutput(
                    login = newUser.login,
                    password = newUser.password,
                    id = newUser.id
                )
            )
        }
    }
}