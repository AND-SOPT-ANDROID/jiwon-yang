package org.sopt.and.data.repositoryimpl

import jakarta.inject.Inject
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.data.dataremote.datasource.UserInfoRemoteDataSource
import org.sopt.and.data.dataremote.model.request.RequestCreateUserDto
import org.sopt.and.data.dataremote.model.request.RequestGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseCreateUserSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserHobbyDto
import org.sopt.and.domain.repository.UserRepository
import retrofit2.Response

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserInfoRemoteDataSource,
    private val userLocalDataSource: UserInfoLocalDataSource
) : UserRepository {
    override suspend fun postSignUp(requestCreateUserDto: RequestCreateUserDto): Response<ResponseCreateUserSuccessDto> =
        userRemoteDataSource.postSignup(requestCreateUserDto)

    override suspend fun postLogin(requestGetUserDto: RequestGetUserDto): Response<ResponseGetUserDto> =
        userRemoteDataSource.postLogin(requestGetUserDto)

    override suspend fun getUserHobby(token: String): Response<ResponseGetUserHobbyDto> =
        userRemoteDataSource.getUserHobby(token)

    override fun saveAccessToken(token: String) {
        userLocalDataSource.accessToken = token
    }

    override fun saveUserName(name: String) {
        userLocalDataSource.userName = name
    }

}