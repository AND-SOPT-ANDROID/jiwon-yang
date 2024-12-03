package org.sopt.and.data.network

import org.sopt.and.data.dto.login.RequestGetUserDto
import org.sopt.and.data.dto.login.ResponseGetUserDto
import org.sopt.and.data.dto.mypage.RequestGetUserHobbyDto
import org.sopt.and.data.dto.mypage.ResponseGetUserHobbyWrapperDto
import org.sopt.and.data.dto.signup.RequestCreateUserDto
import org.sopt.and.data.dto.signup.ResponseCreateUserSuccessDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

//HTTP 메서드를 정의해둔 인터페이스 == service라고 부름.

interface UserService {

    @POST("/user")
    suspend fun signUpUser(
        @Body requestDto: RequestCreateUserDto
    ): Response<ResponseCreateUserSuccessDto>

    @POST("/login")
    fun logInUser(
        @Body request: RequestGetUserDto
    ): Response<ResponseGetUserDto>

    @GET("/user/my-hobby")
    fun getMyHobby(
        @Header("token") request: RequestGetUserHobbyDto
    ): Call<ResponseGetUserHobbyWrapperDto>



}