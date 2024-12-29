package org.sopt.and.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateUserInputUseCase @Inject constructor() {
    companion object {
        const val MAX_USERNAME_LENGTH = 7
    }

    fun stringInputValidCheck(userName: String) : Boolean {
        return userName.length <= MAX_USERNAME_LENGTH
    }

    fun passwordValidCheck(password: String) : Boolean {
        val pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{8,20}$".toRegex()
        return password.matches(pattern)
    }
}