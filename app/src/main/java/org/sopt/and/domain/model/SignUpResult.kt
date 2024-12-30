package org.sopt.and.domain.model

data class SignUpResult (
    val userId: Int? = null,
    val errorCode: String? = null
){
    val isSuccessful: Boolean get() = userId != null
}