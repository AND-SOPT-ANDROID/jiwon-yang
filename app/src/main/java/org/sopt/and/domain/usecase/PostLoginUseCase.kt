package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.request.RequestGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserDto
import org.sopt.and.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(requestGetUserDto: RequestGetUserDto): Response<ResponseGetUserDto> =
        userRepository.postLogin(requestGetUserDto = requestGetUserDto)
}