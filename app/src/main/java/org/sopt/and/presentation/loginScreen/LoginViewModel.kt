package org.sopt.and.presentation.loginScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.presentation.signupScreen.StringInputValidCheck
import org.sopt.and.presentation.signupScreen.PasswordValidCheck

class LoginViewModel : ViewModel() {

    private val _userNameState = MutableStateFlow("")
    val userNameState: StateFlow<String> = _userNameState

    private val _passwordState = MutableStateFlow("")
    val passwordState: StateFlow<String> = _passwordState

    private val _isUserNameValid = MutableStateFlow(false)
    val isUserNameValid: StateFlow<Boolean> = _isUserNameValid

    private val _isPasswordValid = MutableStateFlow(false)
    val isPasswordValid: StateFlow<Boolean> = _isPasswordValid

    private val _shouldShowPassword = MutableStateFlow(false)
    val shouldShowPassword: StateFlow<Boolean> = _shouldShowPassword

    //유저네임 입력 시 입력한 글자 표시
    fun onUserNameChange(newUserName: String) {
        _userNameState.value = newUserName
        _isUserNameValid.value = StringInputValidCheck(newUserName)
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
    fun isLoginValid(userNameText: String, passwordText: String): Boolean {
        return _userNameState.value == userNameText && _passwordState.value == passwordText
    }
}