package org.sopt.and.presentation.loginScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.domain.model.User
import org.sopt.and.data.dataremote.model.request.RequestGetUserDto
import org.sopt.and.data.dataremote.model.response.ResponseGetUserFailedDto
import org.sopt.and.data.dataremote.network.ServicePool
import org.sopt.and.domain.usecase.PostLoginUseCase
import org.sopt.and.domain.usecase.SaveAccessTokenUseCase
import org.sopt.and.domain.usecase.SaveUserNameUseCase
import org.sopt.and.presentation.signupScreen.StringInputValidCheck
import org.sopt.and.presentation.signupScreen.PasswordValidCheck
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userInfoLocalDataSource: UserInfoLocalDataSource,
    private val postLoginUseCase: PostLoginUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
) : ViewModel() {

    //로그인 요청을 서버로 보내기 위함
    private val userService by lazy { ServicePool.userService }

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult = _loginResult.asStateFlow()

    private val _isUserNameValid = MutableStateFlow(false)
    val isUserNameValid: StateFlow<Boolean> = _isUserNameValid

    private val _isPasswordValid = MutableStateFlow(false)
    val isPasswordValid: StateFlow<Boolean> = _isPasswordValid

    private val _shouldShowPassword = MutableStateFlow(false)
    val shouldShowPassword: StateFlow<Boolean> = _shouldShowPassword

    //유저네임 입력 시 입력한 글자 표시
    fun onUserNameChange(newUserName: String) {
        _user.value = _user.value.copy(name = newUserName)
        _isUserNameValid.value = StringInputValidCheck(newUserName)
    }

    //비밀번호 입력 시 입력한 글자 표시
    fun onPasswordChange(newPassword: String) {
        _user.value = _user.value.copy(password = newPassword)
        _isPasswordValid.value = PasswordValidCheck(newPassword)
    }

    // 비밀번호 표시 여부 바꾸기
    fun togglePasswordVisibility() {
        _shouldShowPassword.value = !_shouldShowPassword.value
    }

    // 입력값이 조건에 맞는지 확인 (둘 다 여덟 자 이하?)
    fun isLoginValid(userNameText: String, passwordText: String): Boolean {
        return _isUserNameValid.value && _isPasswordValid.value
    }

    suspend fun logInUser() {
        val requestDto = RequestGetUserDto(
            userName = _user.value.name,
            password = _user.value.password
        )

        try {
            val response = postLoginUseCase(requestDto)
            val token = response.body()?.result?.token

            if(response.isSuccessful && token != null) {
                saveUserNameUseCase(_user.value.name)
                saveAccessTokenUseCase(token)
                _loginResult.value = true
                Log.d(
                    "로그인 요청 성공",
                    "Status code: ${response.code()} token: $token"
                )
            } else {
                val errorBody = response.errorBody()?.string()
                Log.e("서버 응답", "Raw response body: $errorBody")
                val errorCode = if (errorBody != null) {
                    val errorData = Json.decodeFromString<ResponseGetUserFailedDto>(errorBody)
                    errorData.code
                } else {
                    "Unknown error code"
                }
                Log.e("error", "Status code: ${response.code()} and error code: $errorCode")
                _loginResult.value = false
            }
        } catch (e: Exception) {
            Log.e("error", "Exception: ${e.message}")
            _loginResult.value = false
        }

    }
}