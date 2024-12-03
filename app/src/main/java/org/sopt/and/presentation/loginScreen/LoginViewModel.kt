package org.sopt.and.presentation.loginScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.model.dto.login.RequestGetUserDto
import org.sopt.and.model.dto.login.ResponseGetUserWrapperDto
import org.sopt.and.model.dto.mypage.RequestGetUserHobbyDto
import org.sopt.and.model.dto.signup.RequestCreateUserDto
import org.sopt.and.model.network.ServicePool
import org.sopt.and.presentation.main.UserViewModel
import org.sopt.and.presentation.mypageScreen.MypageViewModel
import org.sopt.and.presentation.signupScreen.StringInputValidCheck
import org.sopt.and.presentation.signupScreen.PasswordValidCheck
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(
    private val mypageViewModel: MypageViewModel
) : ViewModel() {

    //로그인 요청을 서버로 보내기 위함
    private val userService by lazy { ServicePool.userService }



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

    fun logInUser(request: RequestGetUserDto, userViewModel: UserViewModel) {
        val TAG = "UserService"

        userService.logInUser(request).enqueue(object : Callback<ResponseGetUserWrapperDto> {
            override fun onResponse(
                call: Call<ResponseGetUserWrapperDto>,
                response: Response<ResponseGetUserWrapperDto>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val token = body.success.result.token

                        Log.d(TAG, "유저 로그인 성공, 유저 토큰(id) : $token")
                        userViewModel.setLoginToken(token)

                        val requestGetUserHobbyDto = RequestGetUserHobbyDto(token = token)

                        val userHobby = mypageViewModel.getUserHobby(requestGetUserHobbyDto, userViewModel)
                        userViewModel.setHobby(userHobby.toString())

                        /*TODO: 이때의 token을 기반으로 user data 받아와 loginViewModel에 값 업데이트 해주기*/


                    } else {
                        Log.e(TAG, "유저 로그인 실패")
                    }
                } else {
                    Log.e(TAG, "유저 로그인 에러 ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResponseGetUserWrapperDto>, t: Throwable) {
                Log.e(TAG, "API 호출 도중 에러 발생: ${t.message}")
            }
        })
    }
}