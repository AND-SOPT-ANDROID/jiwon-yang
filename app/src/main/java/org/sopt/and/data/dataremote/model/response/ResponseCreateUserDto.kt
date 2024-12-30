package org.sopt.and.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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

