package org.sopt.and.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCreateUserWrapperDto( //특정 유저 한 명의 응답 DTO
    @SerialName("success")
    val data: ResponseCreateUserSuccessDto,
    @SerialName("failed")
    val support: ResponseCreateUserFailDto
)


