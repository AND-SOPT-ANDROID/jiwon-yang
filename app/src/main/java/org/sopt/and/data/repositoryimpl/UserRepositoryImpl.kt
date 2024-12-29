package org.sopt.and.data.repositoryimpl

import jakarta.inject.Inject
import kotlinx.serialization.json.Json
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.data.dataremote.datasource.UserInfoRemoteDataSource
import org.sopt.and.data.mapper.UserMapper
import org.sopt.and.domain.model.LoginResult
import org.sopt.and.domain.model.SignUpResult
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.UserRepository

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserInfoRemoteDataSource,
    private val userLocalDataSource: UserInfoLocalDataSource
) : UserRepository {
    override suspend fun postSignUp(user: User): SignUpResult {
        val requestDto = UserMapper.toRequestCreateUserDto(user)
        val response = userRemoteDataSource.postSignup(requestDto)

        return if (response.isSuccessful) {
            response.body()?.let { UserMapper.toSignUpResult(it) }
                ?: throw IllegalStateException("회원가입 실패")
        } else {
            val errorCode = response.errorBody()?.string()?.let { errorBody ->
                Json.decodeFromString<Map<String, String>>(errorBody)["code"]
            }
            SignUpResult(userId = null, errorCode = errorCode ?: "UNKNOWN_ERROR")
        }
    }

    override suspend fun postLogin(user: User): LoginResult {
        val requestDto = UserMapper.toRequestGetUserDto(user)
        val response = userRemoteDataSource.postLogin(requestDto)

        return response.body()?.let { UserMapper.toLoginResult(it) }
            ?: throw IllegalStateException("로그인 실패")
    }

    override suspend fun getUserHobby(token: String): String? {
        val response = userRemoteDataSource.getUserHobby(token)
        return response.body()?.result?.userHobby
    }


    override fun saveAccessToken(token: String) {
        userLocalDataSource.accessToken = token
    }

    override fun saveUserName(name: String) {
        userLocalDataSource.userName = name
    }

}