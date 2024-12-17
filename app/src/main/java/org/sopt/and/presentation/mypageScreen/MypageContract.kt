package org.sopt.and.presentation.mypageScreen

import org.sopt.and.util.base.UiEvent
import org.sopt.and.util.base.UiSideEffect
import org.sopt.and.util.base.UiState

class MypageContract {

    data class MyPageUiState(
        val userName: String = "",
        val userHobby: String = "",
        val accessToken: String = "",
        val contentCount: Int = 0,
    ) : UiState

    sealed class MyPageEvent : UiEvent {
        data object OnLoadUserData : MyPageEvent()
    }

    sealed interface MyPageSideEffect : UiSideEffect {
        data object ShowErrorToast : MyPageSideEffect
    }
}
