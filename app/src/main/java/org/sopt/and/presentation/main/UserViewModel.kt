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

    private val _loginToken = MutableStateFlow("")
    val loginToken: StateFlow<String> = _loginToken

    fun setUserName(newUserName: String) {
        _userName.value = newUserName
    }

    fun setHobby(newHobby: String) {
        _hobby.value = newHobby
    }

    fun setLoginToken(newToken: String) {
        _loginToken.value = newToken
    }

}