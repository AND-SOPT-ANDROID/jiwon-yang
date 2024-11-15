package org.sopt.and.model.dto.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCreateUserFailDto(
    @SerialName("failed")
    val code: FailureResult
)

@Serializable
data class FailureResult(
    @SerialName("code")
    val no: Int
)