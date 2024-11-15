package org.sopt.and.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.presentation.signupScreen.StringInputValidCheck

class UserViewModel : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _hobby = MutableStateFlow("")
    val hobby: StateFlow<String> = _hobby

    fun setUserName(newUserName: String) {
        _userName.value = newUserName
    }

    fun setHobby(newHobbyString: String) {
        _hobby.value = newHobbyString
    }

}