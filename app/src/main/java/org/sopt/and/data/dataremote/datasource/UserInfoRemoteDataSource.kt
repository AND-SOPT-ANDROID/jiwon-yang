package org.sopt.and.data.dataremote.datasource

import org.sopt.and.data.dataremote.model.request.RequestCreateUserDto
import org.sopt.and.data.dataremote.model.request.RequestGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseCreateUserSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserHobbyDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserHobbyFailDto
import retrofit2.Response

interface UserInfoRemoteDataSource {
    suspend fun postSignup(request: RequestCreateUserDto): Response<ResponseCreateUserSuccessDto>

    suspend fun postLogin(request: RequestGetUserDto): Response<ResponseGetUserDto>

    suspend fun getUserHobby(token: String): Response<ResponseGetUserHobbyDto>
}