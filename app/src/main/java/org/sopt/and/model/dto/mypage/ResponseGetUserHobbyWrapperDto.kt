package org.sopt.and.model.dto.mypage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.model.dto.login.ResponseGetUserFailDto
import org.sopt.and.model.dto.login.ResponseGetUserSuccessDto

@Serializable
data class ResponseGetUserHobbyWrapperDto(
    @SerialName("success")
    val success: ResponseGetUserHobbySuccessDto,
    @SerialName("failed")
    val failed: ResponseGetUserHobbyFailDto
)