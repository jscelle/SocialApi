package com.example.feature.news

import kotlinx.serialization.Serializable

@Serializable
data class NewsOutput(
    val id: Int,
    val title: String,
    val description: String
)
