package org.sopt.and.presentation.signupScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json
import org.sopt.and.domain.User
import org.sopt.and.data.dto.signup.RequestCreateUserDto
import org.sopt.and.data.dto.signup.ResponseCreateUserFailedDto
import org.sopt.and.data.dto.signup.ResponseCreateUserSuccessDto
import org.sopt.and.data.network.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {

    //회원가입 성공 시 서버로 create 요청 보내기 위함
    private val userService by lazy { ServicePool.userService }

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _signUpResult = MutableStateFlow<Result<Unit>?>(null)
    val signUpResult: StateFlow<Result<Unit>?> = _signUpResult


    private val _isUserNameValid = MutableStateFlow(true)
    val isUserNameValid: StateFlow<Boolean> = _isUserNameValid

    private val _isPasswordValid = MutableStateFlow(true)
    val isPasswordValid: StateFlow<Boolean> = _isPasswordValid

    private val _isHobbyValid = MutableStateFlow(true)
    val isHobbyValid: StateFlow<Boolean> = _isHobbyValid

    private val _shouldShowPassword = MutableStateFlow(false)
    val shouldShowPassword: StateFlow<Boolean> = _shouldShowPassword


    // 유저 네임 입력 시 입력한 값 보이기
    fun onUserNameChange(newUserName: String) {
        _user.value = _user.value.copy(name = newUserName)
        _isUserNameValid.value = StringInputValidCheck(newUserName)
    }

    // 비밀번호 입력 시 입력한 값 보이기
    fun onPasswordChange(newPassword: String) {
        _user.value = _user.value.copy(password = newPassword)
        _isPasswordValid.value = PasswordValidCheck(newPassword)
    }

    // 취미 입력 시 입력한 값 보이기
    fun onHobbyChange(newHobby: String) {
        _user.value = _user.value.copy(hobby = newHobby)
        _isHobbyValid.value = StringInputValidCheck(newHobby)
    }

    // 비밀번호 노출 상태 변경 시
    fun togglePasswordVisibility() {
        _shouldShowPassword.value = !_shouldShowPassword.value
    }

    fun isSignUpValid(): Boolean {
        return _isUserNameValid.value && _isPasswordValid.value
    }

    suspend fun createNewUser() {
        val requestDto = RequestCreateUserDto(
            userName = _user.value.name,
            password = _user.value.password,
            hobby = _user.value.hobby
        )

        try {
            val response = userService.signUpUser(requestDto)
            if(response.isSuccessful) {
                _signUpResult.value = Result.success(Unit)
                Log.d("로그인 성공", "Status code: ${response.code()}")
            } else {
                val errorBody = response.errorBody()?.string()
                Log.e("서버 응답", "Raw response body: $errorBody")
                val errorCode = if (errorBody != null){
                    val errorData = Json.decodeFromString<ResponseCreateUserFailedDto>(errorBody)
                    errorData.code
                } else {
                    "Unknown error code"
                }

                _signUpResult.value = Result.failure(Exception("Status code is ${response.code()} and error code is $errorCode"))
            }
        } catch (e: Exception) {
            Log.e("Login error", "Exception: ${e.message}")
            _signUpResult.value = Result.failure(e)
        }

    }
}