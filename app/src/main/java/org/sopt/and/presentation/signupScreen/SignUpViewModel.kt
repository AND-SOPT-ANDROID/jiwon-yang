package org.sopt.and.presentation.signupScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel : ViewModel() {

    private val _emailText = MutableStateFlow("")
    val emailText: StateFlow<String> = _emailText

    private val _passwordText = MutableStateFlow("")
    val passwordText: StateFlow<String> = _passwordText

    private val _isEmailValid = MutableStateFlow(true)
    val isEmailValid: StateFlow<Boolean> = _isEmailValid

    private val _isPasswordValid = MutableStateFlow(true)
    val isPasswordValid: StateFlow<Boolean> = _isPasswordValid

    private val _shouldShowPassword = MutableStateFlow(false)
    val shouldShowPassword: StateFlow<Boolean> = _shouldShowPassword


    // 이메일 입력 시 입력한 값 보이기
    fun onEmailChange(newEmail: String) {
        _emailText.value = newEmail
        _isEmailValid.value = EmailValidCheck(newEmail)
    }

    // 비밀번호 입력 시 입력한 값 보이기
    fun onPasswordChange(newPassword: String) {
        _passwordText.value = newPassword
        _isPasswordValid.value = PasswordValidCheck(newPassword)
    }

    // 비밀번호 노출 상태 변경 시
    fun togglePasswordVisibility() {
        _shouldShowPassword.value = !_shouldShowPassword.value
    }

    fun isSignUpValid(): Boolean {
        return _isEmailValid.value && _isPasswordValid.value
    }
}