package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.response.ResponseGetUserHobbyDto
import org.sopt.and.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserHobbyUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String): Response<ResponseGetUserHobbyDto> =
        userRepository.getUserHobby(token = token)
}