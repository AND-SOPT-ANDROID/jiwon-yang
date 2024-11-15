package org.sopt.and.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCreateUserSuccessDto(
    @SerialName("success")
    val result: UserResult
)

@Serializable
data class UserResult(
    @SerialName("no")
    val no: Int
)