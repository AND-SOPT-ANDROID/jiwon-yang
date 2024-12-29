package org.sopt.and.presentation.mypageScreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.GetUserHobbyUseCase
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val userInfoLocalDataSource: UserInfoLocalDataSource,
    private val getUserHobbyUseCase: GetUserHobbyUseCase
) : BaseViewModel<MypageContract.MyPageUiState, MypageContract.MyPageEvent, MypageContract.MyPageSideEffect>() {

    override fun createInitialState(): MypageContract.MyPageUiState = MypageContract.MyPageUiState()

    init {
        setEvent(MypageContract.MyPageEvent.OnLoadUserData)
    }

    override suspend fun handleEvent(event: MypageContract.MyPageEvent) {
        when (event) {
            MypageContract.MyPageEvent.OnLoadUserData -> loadUserData()
        }
    }

    private fun loadUserData() {
        viewModelScope.launch {
            try {
                val userName = userInfoLocalDataSource.userName
                val accessToken = userInfoLocalDataSource.accessToken
                val hobby = getUserHobbyUseCase(accessToken).body()?.result?.userHobby ?: "취미 없음"

                setState {
                    copy(
                        userName = userName,
                        accessToken = accessToken,
                        userHobby = hobby
                    )
                }
            } catch (e: Exception) {
                Log.e("MyPage", "Error loading user data: ${e.message}")
                setSideEffect(MypageContract.MyPageSideEffect.ShowErrorToast)
            }
        }
    }
}
