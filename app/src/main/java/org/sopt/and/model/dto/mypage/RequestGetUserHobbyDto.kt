package org.sopt.and.model.dto.mypage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//요청할 때는 token만 가지고 요청
@Serializable
data class RequestGetUserHobbyDto(
    @SerialName("token")
    val token: String,
)