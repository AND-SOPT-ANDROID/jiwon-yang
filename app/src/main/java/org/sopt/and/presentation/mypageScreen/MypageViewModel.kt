package org.sopt.and.presentation.mypageScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import org.sopt.and.presentation.main.UserViewModel

class MypageViewModel : ViewModel() {

    private val _user = MutableStateFlow(UserViewModel())
    val user: StateFlow<UserViewModel> = _user

    fun getUserName(): String {
        return _user.value.userName.toString()
    }

}