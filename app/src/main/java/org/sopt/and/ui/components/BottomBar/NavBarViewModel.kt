package org.sopt.and.ui.components.BottomBar

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.presentation.mypageScreen.MypageContract
import org.sopt.and.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class NavBarViewModel @Inject constructor(
    private val userInfoLocalDataSource: UserInfoLocalDataSource
) : BaseViewModel<NavBarContract.NavBarUiState, NavBarContract.NavBarEvent, NavBarContract.NavBarSideEffect>() {

    override fun createInitialState(): NavBarContract.NavBarUiState = NavBarContract.NavBarUiState()

    init {
        setEvent(NavBarContract.NavBarEvent.OnLoadUserData)
    }

    override suspend fun handleEvent(event: NavBarContract.NavBarEvent) {
        when (event) {
            NavBarContract.NavBarEvent.OnLoadUserData -> loadUserName()
            is NavBarContract.NavBarEvent.OnPageSelected -> updatePage(event.pageIndex)
        }
    }

    private fun updatePage(pageIndex: Int) {
        setState {
            copy(page = pageIndex)
        }
    }

    private fun loadUserName(){
        viewModelScope.launch {
            try {
                val userName = userInfoLocalDataSource.userName
                val accessToken = userInfoLocalDataSource.accessToken

                setState {
                    copy(
                        userName = userName,
                        accessToken = accessToken,
                    )
                }
            } catch (e: Exception){
                Log.e("NavBar", "Error loading user name: ${e.message}")
                setSideEffect(NavBarContract.NavBarSideEffect.ShowErrorToast)
            }
        }
    }

}