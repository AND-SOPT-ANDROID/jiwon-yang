package org.sopt.and.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserFailedDto(
    @SerialName("code")
    val code: String
)

@Serializable
data class ResponseGetUserDto(
    @SerialName("result")
    val result: UserResult
){
    @Serializable
    data class UserResult(
        @SerialName("token")
        val token: String
    )
}