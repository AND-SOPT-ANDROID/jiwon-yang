package org.sopt.and.data.mapper

import org.sopt.and.data.dataremote.model.request.RequestCreateUserDto
import org.sopt.and.data.dataremote.model.request.RequestGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseCreateUserSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserDto
import org.sopt.and.domain.model.LoginResult
import org.sopt.and.domain.model.SignUpResult
import org.sopt.and.domain.model.User

object UserMapper {

    fun toRequestCreateUserDto(user: User): RequestCreateUserDto {
        return RequestCreateUserDto(
            userName = user.name,
            password = user.password,
            hobby = user.hobby
        )
    }

    fun toRequestGetUserDto(user: User): RequestGetUserDto {
        return RequestGetUserDto(
            userName = user.name,
            password = user.password
        )
    }

    fun toSignUpResult(response: ResponseCreateUserSuccessDto): SignUpResult {
        return SignUpResult(
            userId = response.result.no
        )
    }

    fun toLoginResult(response: ResponseGetUserDto): LoginResult {
        return LoginResult(
            accessToken = response.result.token
        )
    }

}

