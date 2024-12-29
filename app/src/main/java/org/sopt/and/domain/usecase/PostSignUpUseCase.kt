package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.request.RequestCreateUserDto
import org.sopt.and.data.dataremote.model.response.ResponseCreateUserSuccessDto
import org.sopt.and.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostSignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(requestCreateUserDto: RequestCreateUserDto): Response<ResponseCreateUserSuccessDto> =
        userRepository.postSignUp(requestCreateUserDto = requestCreateUserDto)
}