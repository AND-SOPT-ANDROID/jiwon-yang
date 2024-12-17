package org.sopt.and.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserHobbyFailDto(
    @SerialName("code")
    val no: Int
)

@Serializable
data class ResponseGetUserHobbyDto(
    @SerialName("result")
    val result: Result?= null,
){
    @Serializable
    data class Result(
        @SerialName("hobby")
        val userHobby: String? = null,
    )
}

