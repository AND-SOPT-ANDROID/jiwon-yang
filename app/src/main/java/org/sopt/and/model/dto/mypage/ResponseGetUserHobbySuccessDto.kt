package org.sopt.and.model.dto.mypage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserHobbySuccessDto(
    @SerialName("result")
    val result: HobbyResult? = null,
)

@Serializable
data class HobbyResult(
    @SerialName("hobby")
    val userHobby: String? = null,
)
