package org.sopt.and.presentation.signupScreen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.data.dataremote.model.request.RequestCreateUserDto
import org.sopt.and.domain.usecase.PostSignUpUseCase
import org.sopt.and.domain.usecase.ValidateUserInputUseCase
import org.sopt.and.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userInfoLocalDataSource: UserInfoLocalDataSource,
    private val postSignUpUseCase: PostSignUpUseCase,
    private val validateUserInputUseCase: ValidateUserInputUseCase
) : BaseViewModel<SignUpContract.SignUpUiState, SignUpContract.SignUpEvent, SignUpContract.SignUpSideEffect>() {

    //그래서 무슨 값으로 초기화가 되는거지?
    override fun createInitialState(): SignUpContract.SignUpUiState = SignUpContract.SignUpUiState()

    override suspend fun handleEvent(event: SignUpContract.SignUpEvent) {
        when (event) {
            is SignUpContract.SignUpEvent.OnUserNameChanged -> {
                val isValid = validateUserInputUseCase.stringInputValidCheck(event.userName)
                setState { copy(userName = event.userName, isUserNameValid = isValid) }
            }
            is SignUpContract.SignUpEvent.OnPasswordChanged -> {
                val isValid = validateUserInputUseCase.passwordValidCheck(event.password)
                setState { copy(password = event.password, isPasswordValid = isValid) }
            }
            is SignUpContract.SignUpEvent.OnHobbyChanged -> {
                val isValid = validateUserInputUseCase.stringInputValidCheck(event.hobby)
                setState { copy(hobby = event.hobby, isHobbyValid = isValid) }
            }
            SignUpContract.SignUpEvent.OnTogglePasswordVisibility -> {
                setState { copy(shouldShowPassword = !shouldShowPassword) }
            }
            SignUpContract.SignUpEvent.OnSignUpButtonClicked -> {
                attemptSignUp()
            }
        }
    }

    private fun attemptSignUp() {
        viewModelScope.launch {
            val currentState = currentState
            val requestDto = RequestCreateUserDto(
                userName = currentState.userName,
                password = currentState.password,
                hobby = currentState.hobby
            )
            try {
                val response = postSignUpUseCase(requestDto)
                if (response.isSuccessful) {
                    setSideEffect(SignUpContract.SignUpSideEffect.ShowSuccessToast)
                    setSideEffect(SignUpContract.SignUpSideEffect.NavigateToLoginScreen) //인자 넘겨줄 필요 없음.
                } else {
                    setSideEffect(SignUpContract.SignUpSideEffect.ShowErrorToast("회원가입 실패: ${response.code()}"))
                }
            } catch (e: Exception) {
                setSideEffect(SignUpContract.SignUpSideEffect.ShowErrorToast("오류: ${e.message}"))
            }
        }
    }
}
