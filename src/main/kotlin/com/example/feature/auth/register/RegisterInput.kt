package com.example.feature.auth.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterInput(
    val login: String,
    var password: String,
    val isAdmin: Boolean
)
