package org.sopt.and.data.dataremote.network

import org.sopt.and.data.dataremote.model.request.RequestGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserHobbyDto
import org.sopt.and.data.dataremote.model.request.RequestCreateUserDto
import org.sopt.and.data.dataremote.model.response.ResponseCreateUserSuccessDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("/user")
    suspend fun signUpUser( @Body requestCreateUserDto: RequestCreateUserDto )
    : Response<ResponseCreateUserSuccessDto>

    @POST("/login")
    suspend fun logInUser( @Body requestGetUserDto: RequestGetUserDto
    ): Response<ResponseGetUserDto>

    @GET("/user/my-hobby")
    suspend fun getMyHobby( @Header("token") token: String
    ): Response<ResponseGetUserHobbyDto>



}