package com.example.feature.comments

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureCommentsRouting() {
    val manager = CommentsManager()

    routing {
        authenticate("auth-basic") {
            get("/comments/") {
                manager.getComments(call = call)
            }
            post("/comments/") {
                manager.postComment(call = call)
            }
        }
    }
}