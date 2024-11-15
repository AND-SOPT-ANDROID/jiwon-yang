package org.sopt.and.model.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserWrapperDto(
    @SerialName("success")
    val success: ResponseGetUserSuccessDto,
    @SerialName("failed")
    val failed: ResponseGetUserFailDto
)