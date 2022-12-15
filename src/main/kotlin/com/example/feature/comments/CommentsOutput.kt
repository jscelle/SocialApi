package com.example.feature.comments

import kotlinx.serialization.Serializable

@Serializable
data class CommentsOutput(
    val authorId: Int,
    val newsId: Int,
    val content: String
)
