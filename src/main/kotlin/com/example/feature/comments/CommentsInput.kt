package com.example.feature.comments

import kotlinx.serialization.Serializable

@Serializable
data class CommentsPostInput(
    val authorId: Int,
    val newsId: Int,
    val content: String
)

@Serializable
data class CommentsGetInput(
    val newsId: Int
)
