package org.sopt.and.presentation.LoginScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.presentation.signupScreen.EmailValidCheck
import org.sopt.and.presentation.signupScreen.PasswordValidCheck

class LoginViewModel : ViewModel() {

    private val _emailState = MutableStateFlow("")
    val emailState: StateFlow<String> = _emailState

    private val _passwordState = MutableStateFlow("")
    val passwordState: StateFlow<String> = _passwordState

    private val _isEmailValid = MutableStateFlow(false)
    val isEmailValid: StateFlow<Boolean> = _isEmailValid

    private val _isPasswordValid = MutableStateFlow(false)
    val isPasswordValid: StateFlow<Boolean> = _isPasswordValid

    private val _shouldShowPassword = MutableStateFlow(false)
    val shouldShowPassword: StateFlow<Boolean> = _shouldShowPassword

    //이메일 입력 시 입력한 글자 표시
    fun onEmailChange(newEmail: String) {
        _emailState.value = newEmail
        _isEmailValid.value = EmailValidCheck(newEmail)
    }

    //비밀번호 입력 시 입력한 글자 표시
    fun onPasswordChange(newPassword: String) {
        _passwordState.value = newPassword
        _isPasswordValid.value = PasswordValidCheck(newPassword)
    }

    // 비밀번호 표시 여부 바꾸기
    fun togglePasswordVisibility() {
        _shouldShowPassword.value = !_shouldShowPassword.value
    }

    // 로그인 검증 로직
    fun isLoginValid(emailText: String, passwordText: String): Boolean {
        return _emailState.value == emailText && _passwordState.value == passwordText
    }
}