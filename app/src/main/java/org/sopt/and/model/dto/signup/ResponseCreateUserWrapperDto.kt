package org.sopt.and.model.dto.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCreateUserWrapperDto( //특정 유저 한 명의 응답 DTO
    @SerialName("success")
    val success: ResponseCreateUserSuccessDto,
    @SerialName("failed")
    val failed: ResponseCreateUserFailDto
)


