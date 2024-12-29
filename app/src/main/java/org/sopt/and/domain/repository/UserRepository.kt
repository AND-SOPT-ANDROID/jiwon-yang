package org.sopt.and.domain.repository

import org.sopt.and.domain.model.LoginResult
import org.sopt.and.domain.model.SignUpResult
import org.sopt.and.domain.model.User

interface UserRepository {
    suspend fun postSignUp(user: User): SignUpResult
    suspend fun postLogin(user: User): LoginResult
    suspend fun getUserHobby(token: String): String?

    fun saveAccessToken(token: String)
    fun saveUserName(name: String)
}