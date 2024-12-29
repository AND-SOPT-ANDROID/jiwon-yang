package org.sopt.and.domain.usecase

import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserHobbyUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String): String? =
        userRepository.getUserHobby(token = token)
}