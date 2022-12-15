package com.example.database.comments

data class CommentsDTO(
    val authorId: Int,
    val newsId: Int,
    val content: String
)
