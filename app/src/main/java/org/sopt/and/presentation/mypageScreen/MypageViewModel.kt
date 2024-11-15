package org.sopt.and.presentation.mypageScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.model.dto.login.ResponseGetUserSuccessDto
import org.sopt.and.model.dto.mypage.RequestGetUserHobbyDto
import org.sopt.and.model.dto.mypage.ResponseGetUserHobbyWrapperDto
import org.sopt.and.model.network.ServicePool
import retrofit2.Callback

import org.sopt.and.presentation.main.UserViewModel
import retrofit2.Call
import retrofit2.Response

class MypageViewModel : ViewModel() {

    //현재 로그인한 토큰으로 현 사용자의 취미를 불러오기 위함
    private val userService by lazy { ServicePool.userService }

    private val _user = MutableStateFlow(UserViewModel())
    val user: StateFlow<UserViewModel> = _user

    fun getUserName(): String {
        return _user.value.userName.toString()
    }

    fun getUserHobby(request: RequestGetUserHobbyDto, userViewModel: UserViewModel) {
        val TAG = "UserService"

        userService.getMyHobby(request).enqueue(object : Callback<ResponseGetUserHobbyWrapperDto> {
            override fun onResponse(
                call: Call<ResponseGetUserHobbyWrapperDto>,
                response: Response<ResponseGetUserHobbyWrapperDto>
            ) {
                if (response.isSuccessful) {
                    // 성공 응답 처리
                    val body = response.body()
                    if (body != null) {
                        val userHobby = body.success.result?.userHobby ?: "default hobby"
                        Log.d("UserService", "유저 취미: $userHobby")
                        userViewModel.setHobby(userHobby)
                    } else {
                        Log.e("UserService", "응답 본문 없음")
                    }
                } else {
                    // 실패 응답 처리
                }
            }

            override fun onFailure(call: Call<ResponseGetUserHobbyWrapperDto>, t: Throwable) {
                // 네트워크 오류 처리
                Log.e("UserService", "API 호출 실패: ${t.message}")
            }
        })

    }
}