package org.sopt.and.model.dto.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCreateUserFailedDto(
    @SerialName("code")
    val code: String
)

@Serializable
data class ResponseCreateUserSuccessDto(
    @SerialName("result")
    val result: UserResult
){
    @Serializable
    data class UserResult(
        @SerialName("no")
        val no: Int
    )
}

