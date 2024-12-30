package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.LoginResult
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): LoginResult =
        userRepository.postLogin(user)
}