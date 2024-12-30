package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.SignUpResult
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostSignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): SignUpResult =
        userRepository.postSignUp(user)
}