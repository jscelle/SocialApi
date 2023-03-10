package com.example.feature.comments

import com.example.database.comments.Comments
import com.example.database.comments.CommentsDTO
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class CommentsManager {
    suspend fun postComment(call: ApplicationCall) {
        val comment = call.receive<CommentsPostInput>()

        Comments.insert(
            comment = CommentsDTO(
                authorId = comment.authorId,
                newsId = comment.newsId,
                content = comment.content
            )
        )

        call.respond(comment)
    }

    suspend fun getComments(id: Int, call: ApplicationCall) {

        val comments = Comments
            .getById(id)
            .map {
            CommentsOutput(
                authorId = it.authorId,
                newsId = it.newsId,
                content = it.content
            )
        }

        call.respond(comments)
    }
}