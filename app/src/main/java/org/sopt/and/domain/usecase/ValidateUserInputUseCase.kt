package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.response.ResponseGetUserHobbyDto
import org.sopt.and.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateUserInputUseCase @Inject constructor() {
    fun stringInputValidCheck(userInput: String) : Boolean {
        return userInput.length <= 7
    }

    fun passwordValidCheck(password: String) : Boolean {
        val pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{8,20}$".toRegex()
        return password.matches(pattern)
    }
}