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

}