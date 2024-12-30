package org.sopt.and.ui.components.BottomBar

import org.sopt.and.util.base.UiEvent
import org.sopt.and.util.base.UiSideEffect
import org.sopt.and.util.base.UiState

class NavBarContract {

    data class NavBarUiState(
        val userName: String = "",
        val accessToken: String = "",
        val page: Int = 0,
    ) : UiState

    sealed class NavBarEvent : UiEvent {
        data object OnLoadUserData : NavBarEvent()
        data class OnPageSelected(val pageIndex: Int) : NavBarEvent()
    }

    sealed interface NavBarSideEffect : UiSideEffect {
        data object ShowErrorToast : NavBarSideEffect
    }
}