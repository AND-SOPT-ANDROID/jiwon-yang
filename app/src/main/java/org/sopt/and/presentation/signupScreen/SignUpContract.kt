package org.sopt.and.presentation.signupScreen

import org.sopt.and.util.base.UiEvent
import org.sopt.and.util.base.UiSideEffect
import org.sopt.and.util.base.UiState

class SignUpContract {

    data class SignUpUiState(
        val userName: String = "",
        val password: String = "",
        val hobby: String = "",
        val isUserNameValid: Boolean = true,
        val isPasswordValid: Boolean = true,
        val isHobbyValid: Boolean = true,
        val shouldShowPassword: Boolean = false
    ) : UiState

    sealed class SignUpEvent : UiEvent {
        data class OnUserNameChanged(val userName: String) : SignUpEvent()
        data class OnPasswordChanged(val password: String) : SignUpEvent()
        data class OnHobbyChanged(val hobby: String) : SignUpEvent()
        data object OnTogglePasswordVisibility : SignUpEvent()
        data object OnSignUpButtonClicked : SignUpEvent()
    }

    sealed interface SignUpSideEffect : UiSideEffect {
        data object ShowSuccessToast : SignUpSideEffect
        data class ShowErrorToast(val message: String) : SignUpSideEffect
        data class NavigateToLoginScreen(val userName: String, val password: String) : SignUpSideEffect
    }
}
