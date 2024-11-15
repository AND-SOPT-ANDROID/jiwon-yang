package org.sopt.and.model.network

import org.sopt.and.model.dto.RequestCreateUserDto
import org.sopt.and.model.dto.ResponseCreateUserWrapperDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

//HTTP 메서드를 정의해둔 인터페이스 == service라고 부름.

interface UserService {

    //유저 회원가입
    @POST("/user")
    fun signUpUser(
        @Body request: RequestCreateUserDto
    ): Call<ResponseCreateUserWrapperDto>
    //여기서 Call : 응답이 왔을 때 불려질 타입

}