package org.sopt.and.domain.usecase

import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.domain.model.User
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userInfoLocalDataSource: UserInfoLocalDataSource
){
    operator fun invoke(): User {
        val userName = userInfoLocalDataSource.userName
        val accessToken = userInfoLocalDataSource.accessToken
        val hobby = userInfoLocalDataSource.hobby
        return User(userName, accessToken, hobby)
    }
}
