package org.sopt.and.presentation.signupScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.model.dto.signup.RequestCreateUserDto
import org.sopt.and.model.dto.signup.ResponseCreateUserWrapperDto
import org.sopt.and.model.network.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {

    //회원가입 성공 시 서버로 create 요청 보내기 위함
    private val userService by lazy { ServicePool.userService }

    private val _userNameText = MutableStateFlow("")
    val userNameText: StateFlow<String> = _userNameText

    private val _passwordText = MutableStateFlow("")
    val passwordText: StateFlow<String> = _passwordText

    private val _hobbyText = MutableStateFlow("")
    val hobbyText: StateFlow<String> = _hobbyText

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
        _userNameText.value = newUserName
        _isUserNameValid.value = StringInputValidCheck(newUserName)
    }

    // 비밀번호 입력 시 입력한 값 보이기
    fun onPasswordChange(newPassword: String) {
        _passwordText.value = newPassword
        _isPasswordValid.value = PasswordValidCheck(newPassword)
    }

    // 비밀번호 입력 시 입력한 값 보이기
    fun onHobbyChange(newHobby: String) {
        _hobbyText.value = newHobby
        _isHobbyValid.value = StringInputValidCheck(newHobby)
    }

    // 비밀번호 노출 상태 변경 시
    fun togglePasswordVisibility() {
        _shouldShowPassword.value = !_shouldShowPassword.value
    }

    fun isSignUpValid(): Boolean {
        return _isUserNameValid.value && _isPasswordValid.value
    }

    fun createNewUser(request: RequestCreateUserDto) {
        val TAG = "UserService"

        userService.signUpUser(request).enqueue(object : Callback<ResponseCreateUserWrapperDto> {

            override fun onResponse(
                call: Call<ResponseCreateUserWrapperDto>,
                response: Response<ResponseCreateUserWrapperDto>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Log.d(TAG, "유저 생성 성공 ${body.success.result.no}")
                    } else {
                        Log.e(TAG, "유저 생성 에러")
                    }
                } else {
                    Log.e(TAG, "유저 생성 에러 ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResponseCreateUserWrapperDto>, t: Throwable) {
                Log.e(TAG, "API 호출 도중 에러 발생: ${t.message}")
            }
        })
    }




}