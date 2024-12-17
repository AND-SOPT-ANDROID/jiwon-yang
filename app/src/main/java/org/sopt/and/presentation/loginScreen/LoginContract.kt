package org.sopt.and.presentation.loginScreen

import org.sopt.and.util.base.UiEvent
import org.sopt.and.util.base.UiSideEffect
import org.sopt.and.util.base.UiState

class LoginContract {

    data class LoginUiState(
        val userName: String = "",
        val password: String = "",
        val isUserNameValid: Boolean = false,
        val isPasswordValid: Boolean = false,
        val isLoading: Boolean = false,
        val loginResult: Boolean? = null,
        val shouldShowPassword: Boolean = false
    ) : UiState

    sealed class LoginEvent : UiEvent {
        data class OnUserNameChanged(val userName: String) : LoginEvent()
        data class OnPasswordChanged(val password: String) : LoginEvent()
        data object OnLoginButtonClicked : LoginEvent()
        data object OnTogglePasswordVisibility : LoginEvent()
    }

    sealed interface LoginSideEffect : UiSideEffect {
        data object NavigateToHome : LoginSideEffect
        data class ShowSnackbar(val message: String) : LoginSideEffect
    }
}
