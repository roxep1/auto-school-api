package com.bashkir.models

import kotlinx.serialization.Serializable

@Serializable
data class Cred(
    val login: String,
    val password: String
)