package org.sopt.and.presentation.homeScreen

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.and.R
import org.sopt.and.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeContract.HomeUiState, HomeContract.HomeEvent, HomeContract.HomeSideEffect>() {

    override fun createInitialState(): HomeContract.HomeUiState {
        return HomeContract.HomeUiState(
            pagerImages = listOf(
                R.drawable.food_pic1,
                R.drawable.food_pic2,
                R.drawable.food_pic3,
                R.drawable.food_pic4,
                R.drawable.food_pic5
            )
        )
    }

    override suspend fun handleEvent(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.OnImageClicked -> {
                setSideEffect(HomeContract.HomeSideEffect.NavigateToDetail(event.imageIndex))
            }
            is HomeContract.HomeEvent.OnScreenLoaded -> {
                setState { copy(isLoading = true) }
            }
        }
    }
}
