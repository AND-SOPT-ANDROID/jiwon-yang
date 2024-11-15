package org.sopt.and.model.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserFailDto(
    @SerialName("code")
    val no: Int
)
