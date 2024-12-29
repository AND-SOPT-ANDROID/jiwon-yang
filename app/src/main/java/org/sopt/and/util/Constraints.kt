package org.sopt.and.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object HomeScreen : Route()

    @Serializable
    data class SignUpScreen(
        val userName: String,
        val password: String
    ) : Route()

    @Serializable
    data object LoginScreen : Route()

    @Serializable
    data class MypageScreen(
        val userName: String
    ) : Route()

    @Serializable
    data object SearchScreen : Route()
}