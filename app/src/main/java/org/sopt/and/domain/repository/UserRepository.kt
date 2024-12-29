package org.sopt.and.domain.repository

import org.sopt.and.data.dataremote.model.request.RequestCreateUserDto
import org.sopt.and.data.dataremote.model.request.RequestGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseCreateUserSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserHobbyDto
import retrofit2.Response

interface UserRepository {
    suspend fun postSignUp(requestCreateUserDto: RequestCreateUserDto): Response<ResponseCreateUserSuccessDto>
    suspend fun postLogin(requestGetUserDto: RequestGetUserDto): Response<ResponseGetUserDto>
    suspend fun getUserHobby(token: String): Response<ResponseGetUserHobbyDto>

    fun saveAccessToken(token: String)
    fun saveUserName(name: String)
}