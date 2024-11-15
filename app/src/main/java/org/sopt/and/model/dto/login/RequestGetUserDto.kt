package org.sopt.and.model.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//요청할 때는 username만 가지고 요청 (unique해야 함)
@Serializable
data class RequestGetUserDto(
    @SerialName("username")
    val userName: String,
)