package org.sopt.and.data.dataremote.datasourceimpl

import org.sopt.and.data.dataremote.datasource.UserInfoRemoteDataSource
import org.sopt.and.data.dataremote.model.request.RequestCreateUserDto
import org.sopt.and.data.dataremote.model.request.RequestGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseCreateUserSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserHobbyDto
import org.sopt.and.data.dataremote.network.UserService
import retrofit2.Response
import javax.inject.Inject

class UserInfoRemoteDataSourceImpl @Inject constructor (
    private val service: UserService
) : UserInfoRemoteDataSource {
    override suspend fun postSignup(requestCreateUserDto: RequestCreateUserDto): Response<ResponseCreateUserSuccessDto> =
        service.signUpUser(requestCreateUserDto)

    override suspend fun postLogin(requestGetUserDto: RequestGetUserDto): Response<ResponseGetUserDto> =
        service.logInUser(requestGetUserDto)

    override suspend fun getUserHobby(token: String): Response<ResponseGetUserHobbyDto> =
        service.getMyHobby(token)
}