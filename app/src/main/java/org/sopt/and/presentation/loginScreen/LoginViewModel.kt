package org.sopt.and.presentation.loginScreen

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.and.domain.model.User
import org.sopt.and.domain.usecase.PostLoginUseCase
import org.sopt.and.domain.usecase.SaveAccessTokenUseCase
import org.sopt.and.domain.usecase.SaveUserNameUseCase
import org.sopt.and.domain.usecase.ValidateUserInputUseCase
import org.sopt.and.util.base.BaseViewModel
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val validateUserInputUseCase: ValidateUserInputUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
) : BaseViewModel<LoginContract.LoginUiState, LoginContract.LoginEvent, LoginContract.LoginSideEffect>() {

    override fun createInitialState() = LoginContract.LoginUiState()

    override suspend fun handleEvent(event: LoginContract.LoginEvent) {
        when (event) {

            is LoginContract.LoginEvent.OnUserNameChanged -> {
                val isValid = validateUserInputUseCase.stringInputValidCheck(event.userName)
                setState { copy(userName = event.userName, isUserNameValid = isValid) }
            }
            is LoginContract.LoginEvent.OnPasswordChanged -> {
                val isValid = validateUserInputUseCase.passwordValidCheck(event.password)
                setState { copy(password = event.password, isPasswordValid = isValid) }
            }
            LoginContract.LoginEvent.OnTogglePasswordVisibility -> {
                setState { copy(shouldShowPassword = !shouldShowPassword) }
            }
            LoginContract.LoginEvent.OnLoginButtonClicked -> {
                setState { copy(isLoading = true) }
                attemptLogin()
            }
        }
    }

    private suspend fun attemptLogin() {

        val currentState = currentState

        try {
            val user = User(
                name = currentState.userName,
                password = currentState.password,
                hobby = ""
            )

            val loginResult = postLoginUseCase(user)

            saveUserNameUseCase(currentState.userName)
            saveAccessTokenUseCase(loginResult.accessToken)
            setSideEffect(LoginContract.LoginSideEffect.NavigateToHome)
            setSideEffect(LoginContract.LoginSideEffect.ShowSnackbar("로그인에 성공했습니다."))

        } catch (e: HttpException) {
            when (e.code()){
                400 -> setSideEffect(LoginContract.LoginSideEffect.ShowSnackbar("로그인 요청 정보가 올바르지 않습니다."))
                403 -> setSideEffect(LoginContract.LoginSideEffect.ShowSnackbar("유저 이름 혹은 비밀번호가 일치하지 않습니다."))
                404 -> setSideEffect(LoginContract.LoginSideEffect.ShowSnackbar("유효하지 않은 경로로 요청이 들어왔습니다.\n 처음부터 재시도하세요."))
            }
        } catch (e: Exception) {
            setSideEffect(LoginContract.LoginSideEffect.ShowSnackbar("로그인 요청 중 오류가 발생했습니다."))
        } finally {
            setState { copy(isLoading = false) }
        }
    }
}
