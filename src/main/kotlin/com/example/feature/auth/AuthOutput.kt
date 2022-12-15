package com.example.feature.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthOutput(
    val login: String,
    val password: String,
    val id: Int
)
