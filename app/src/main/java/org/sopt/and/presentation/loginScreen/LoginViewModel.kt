package org.sopt.and.presentation.loginScreen

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.and.domain.usecase.PostLoginUseCase
import org.sopt.and.domain.usecase.SaveAccessTokenUseCase
import org.sopt.and.domain.usecase.SaveUserNameUseCase
import org.sopt.and.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
) : BaseViewModel<LoginContract.LoginUiState, LoginContract.LoginEvent, LoginContract.LoginSideEffect>() {

    override fun createInitialState() = LoginContract.LoginUiState()

    override suspend fun handleEvent(event: LoginContract.LoginEvent) {
        when (event) {
            is LoginContract.LoginEvent.OnUserNameChanged -> {
                setState { copy(userName = event.userName, isUserNameValid = event.userName.length <= 7) }
            }
            is LoginContract.LoginEvent.OnPasswordChanged -> {
                setState { copy(password = event.password, isPasswordValid = event.password.length >= 8) }
            }
            LoginContract.LoginEvent.OnTogglePasswordVisibility -> {
                setState { copy(shouldShowPassword = !shouldShowPassword) }
            }
            LoginContract.LoginEvent.OnLoginButtonClicked -> {
                attemptLogin()
            }
        }
    }

    private suspend fun attemptLogin() {
        setState { copy(isLoading = true) }

        val currentState = currentState
        val requestDto = org.sopt.and.data.dataremote.model.request.RequestGetUserDto(
            userName = currentState.userName,
            password = currentState.password
        )

        try {
            val response = postLoginUseCase(requestDto)
            if (response.isSuccessful && response.body()?.result?.token != null) {
                saveUserNameUseCase(currentState.userName)
                saveAccessTokenUseCase(response.body()!!.result.token)
                sendSideEffect(LoginContract.LoginSideEffect.NavigateToHome)
            } else {
                sendSideEffect(LoginContract.LoginSideEffect.ShowSnackbar("유저 이름 혹은 비밀번호를 확인하세요."))
            }
        } catch (e: Exception) {
            sendSideEffect(LoginContract.LoginSideEffect.ShowSnackbar("로그인 요청 중 오류가 발생했습니다."))
        } finally {
            setState { copy(isLoading = false) }
        }
    }
}
