package com.example.feature.auth.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginInput(
    val login: String,
    val password: String
)