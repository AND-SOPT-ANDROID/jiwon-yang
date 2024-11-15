package org.sopt.and.model.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserSuccessDto(
    @SerialName("result")
    val result: UserResult
)

@Serializable
data class UserResult(
    @SerialName("token")
    val token: String
)