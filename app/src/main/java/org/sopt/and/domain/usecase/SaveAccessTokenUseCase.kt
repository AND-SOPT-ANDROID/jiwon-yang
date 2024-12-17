package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.response.ResponseGetUserHobbyDto
import org.sopt.and.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveAccessTokenUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(token: String) {
        userRepository.saveAccessToken(token)
    }
}