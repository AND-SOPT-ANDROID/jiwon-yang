package org.sopt.and.model.dto.mypage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserHobbyFailDto(
    @SerialName("code")
    val no: Int
)