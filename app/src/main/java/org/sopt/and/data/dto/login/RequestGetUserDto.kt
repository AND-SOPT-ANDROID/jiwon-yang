package org.sopt.and.data.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestGetUserDto(
    @SerialName("username")
    val userName: String,
    @SerialName("password")
    val password: String
)