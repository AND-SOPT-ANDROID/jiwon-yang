package org.sopt.and.data.dataremote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCreateUserDto(
    @SerialName("username")
    val userName: String,
    @SerialName("password")
    val password: String,
    @SerialName("hobby")
    val hobby: String
)