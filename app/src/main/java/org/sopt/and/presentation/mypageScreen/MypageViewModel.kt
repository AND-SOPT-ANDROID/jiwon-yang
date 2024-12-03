package org.sopt.and.presentation.mypageScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.data.network.ServicePool
import org.sopt.and.domain.User
import org.sopt.and.presentation.main.UserViewModel


class MypageViewModel(
    private val userInfoLocalDataSource: UserInfoLocalDataSource
) : ViewModel() {

    private val userService by lazy { ServicePool.userService }

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    init {
        loadUserData()
    }

    fun loadUserData() {
        viewModelScope.launch {
            val userName = userInfoLocalDataSource.userName
            val accessToken = userInfoLocalDataSource.accessToken
            val hobby = getUserHobby()
            _user.value.name = userName
            _user.value.hobby = hobby
            _user.value.accessToken = accessToken
        }
    }

    suspend fun getUserHobby(): String {
        try {
            val response = userService.getMyHobby(userInfoLocalDataSource.accessToken)
            if (response.isSuccessful) {
                Log.d("취미 조회 API 성공", "status code: ${response.code()}")
                return response.body()?.result?.userHobby ?: "hobby"
            } else {
                Log.d("에러", "status code: ${response.code()}")
                return "취미 가져오기 에러"
            }
        } catch (e: Exception) {
            Log.e("error", "Exception: ${e.message}")
            return "취미 가져오기 에러"
        }
    }



//    fun getUserHobby(request: RequestGetUserHobbyDto, userViewModel: UserViewModel) {
//        val TAG = "UserService"
//
//        userService.getMyHobby(request).enqueue(object : Callback<ResponseGetUserHobbyWrapperDto> {
//            override fun onResponse(
//                call: Call<ResponseGetUserHobbyWrapperDto>,
//                response: Response<ResponseGetUserHobbyWrapperDto>
//            ) {
//                if (response.isSuccessful) {
//                    // 성공 응답 처리
//                    val body = response.body()
//                    if (body != null) {
//                        val userHobby = body.success.result?.userHobby ?: "default hobby"
//                        Log.d("UserService", "유저 취미: $userHobby")
//                        userViewModel.setHobby(userHobby)
//                    } else {
//                        Log.e("UserService", "응답 본문 없음")
//                    }
//                } else {
//                    // 실패 응답 처리
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseGetUserHobbyWrapperDto>, t: Throwable) {
//                // 네트워크 오류 처리
//                Log.e("UserService", "API 호출 실패: ${t.message}")
//            }
//        })
//
//    }
}