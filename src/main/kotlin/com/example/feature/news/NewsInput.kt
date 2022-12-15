package com.example.feature.news

import kotlinx.serialization.Serializable

@Serializable
data class NewsInput(
    val title: String,
    val description: String
)
