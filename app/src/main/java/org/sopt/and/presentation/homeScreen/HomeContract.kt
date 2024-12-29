package org.sopt.and.presentation.homeScreen

import androidx.compose.runtime.Immutable
import org.sopt.and.util.base.UiEvent
import org.sopt.and.util.base.UiSideEffect
import org.sopt.and.util.base.UiState

class HomeContract {

    @Immutable
    data class HomeUiState(
        val pagerImages: List<Int> = listOf(),
        val isLoading: Boolean = false
    ) : UiState

    sealed interface HomeSideEffect : UiSideEffect {
        data class NavigateToDetail(val imageIndex: Int) : HomeSideEffect
    }

    sealed class HomeEvent : UiEvent {
        data object OnScreenLoaded : HomeEvent()
        data class OnImageClicked(val imageIndex: Int) : HomeEvent()
    }
}

