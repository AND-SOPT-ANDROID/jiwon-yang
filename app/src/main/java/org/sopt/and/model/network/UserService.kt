package org.sopt.and.model.network

import org.sopt.and.model.dto.login.RequestGetUserDto
import org.sopt.and.model.dto.login.ResponseGetUserFailDto
import org.sopt.and.model.dto.login.ResponseGetUserSuccessDto
import org.sopt.and.model.dto.login.ResponseGetUserWrapperDto
import org.sopt.and.model.dto.mypage.RequestGetUserHobbyDto
import org.sopt.and.model.dto.mypage.ResponseGetUserHobbyWrapperDto
import org.sopt.and.model.dto.signup.RequestCreateUserDto
import org.sopt.and.model.dto.signup.ResponseCreateUserSuccessDto
import org.sopt.and.model.dto.signup.ResponseCreateUserWrapperDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

//HTTP 메서드를 정의해둔 인터페이스 == service라고 부름.

interface UserService {

    @POST("/user")
    fun signUpUser(
        @Body request: RequestCreateUserDto
    ): Call<ResponseCreateUserWrapperDto>   //Call : 돌아오는 응답의 타입

    @POST("/login")
    fun logInUser(
        @Body request: RequestGetUserDto
    ): Call<ResponseGetUserWrapperDto>

    @GET("/user/my-hobby")
    fun getMyHobby(
        @Header("token") request: RequestGetUserHobbyDto
    ): Call<ResponseGetUserHobbyWrapperDto>



}