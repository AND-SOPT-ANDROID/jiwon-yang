package org.sopt.and.presentation.signupScreen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.model.User
import org.sopt.and.domain.usecase.PostSignUpUseCase
import org.sopt.and.domain.usecase.ValidateUserInputUseCase
import org.sopt.and.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val postSignUpUseCase: PostSignUpUseCase,
    private val validateUserInputUseCase: ValidateUserInputUseCase
) : BaseViewModel<SignUpContract.SignUpUiState, SignUpContract.SignUpEvent, SignUpContract.SignUpSideEffect>() {

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
            is SignUpContract.SignUpEvent.OnTogglePasswordVisibility -> {
                setState { copy(shouldShowPassword = !shouldShowPassword) }
            }
            is SignUpContract.SignUpEvent.OnSignUpButtonClicked -> {
                viewModelScope.launch {
                    setState { copy(isLoading = true) }
                    attemptSignUp()
                }
            }
        }
    }

    private fun attemptSignUp() {
        viewModelScope.launch {

            try {
                val currentState = currentState
                val user = User(
                    name = currentState.userName,
                    password = currentState.password,
                    hobby = currentState.hobby
                )

                val signUpResult = postSignUpUseCase(user)

                if (signUpResult.isSuccessful) {
                    setSideEffect(SignUpContract.SignUpSideEffect.ShowSuccessToast)
                    setSideEffect(SignUpContract.SignUpSideEffect.NavigateToLoginScreen)
                } else {
                    SignUpContract.SignUpSideEffect.ShowErrorToast("회원가입 실패")
                }

            } catch (e: Exception) {
                setSideEffect(SignUpContract.SignUpSideEffect.ShowErrorToast("오류: ${e.message}"))
            } finally {
                setState { copy(isLoading = false) }
            }
        }
    }
}
