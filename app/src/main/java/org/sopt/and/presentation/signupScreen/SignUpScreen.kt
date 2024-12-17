package org.sopt.and.presentation.signupScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.User
import org.sopt.and.ui.components.SignUpandLogIn.SignUpTextField
import org.sopt.and.ui.components.SignUpandLogIn.SocialLoginSection
import org.sopt.and.ui.theme.ANDANDROIDTheme

fun StringInputValidCheck(newString: String): Boolean {
    var isValid = false
    val inputStr : CharSequence = newString

    if(inputStr.length >= 8){
        return isValid
    } else {
        return !isValid
    }
}

fun PasswordValidCheck(password: String): Boolean {
    val pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)|(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*])|(?=.*[a-z])(?=.*\\d)(?=.*[!@#\$%^&*])|(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{1,8}\$".toRegex()
    return password.matches(pattern)
}


@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToLoginScreen: (user: User) -> Unit = {},
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var toastMessage = ""

    var userNameText = remember { mutableStateOf("") }
    var passwordText = remember { mutableStateOf("") }
    var hobbyText = remember { mutableStateOf("") }

    var isUserNameValid = signUpViewModel.isUserNameValid.collectAsState().value
    var isPasswordValid = signUpViewModel.isPasswordValid.collectAsState().value
    var isHobbyValid = signUpViewModel.isHobbyValid.collectAsState().value
    var shouldShowPassword = signUpViewModel.shouldShowPassword.collectAsState().value

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1B1B))
            .padding(25.dp),
    ){
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "회원가입",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Color.White

        )
        Spacer(modifier = Modifier.weight(0.35f))

        Text(
            "유저 이름, 비밀번호, 취미 입력만으로\nWavve를 즐길 수 있어요!",
            color = Color.White,
            fontSize = 21.sp
        )

        Spacer(modifier = Modifier.weight(0.25f))

        SignUpTextField(
            text = userNameText.value,
            onValueChange = {
                userNameText.value = it
                signUpViewModel.onUserNameChange(it)
                isUserNameValid = StringInputValidCheck(it)
            },
            fieldType = "userName",
            conditionCheck = isUserNameValid,
            errMessage = "유저 이름은 7자 이하여야 합니다.",
            placeholder = "유저 이름 (7자 이하)",
            descriptionText = "로그인, 비밀번호 찾기, 알림에 사용되니 정확하게 입력해주세요.",
        )

        Spacer(modifier = Modifier.weight(0.15f))

        SignUpTextField(
            text = passwordText.value,
            onValueChange = {
                passwordText.value = it
                signUpViewModel.onPasswordChange(it) /*todo: onpasswordchange 안에 isvalid 체킹하는 로직을 넣기.*/
                isPasswordValid = PasswordValidCheck(it)
            },
            fieldType = "Password",
            conditionCheck = isPasswordValid,
            errMessage = "올바른 비밀번호 형식이 아닙니다.",
            placeholder = "Wavve 비밀번호 설정",
            shouldShowPassword = shouldShowPassword,
            onPasswordVisibilityChange = {
                signUpViewModel.togglePasswordVisibility()
            },
            descriptionText = "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.",
        )

        SignUpTextField(
            text = hobbyText.value,
            onValueChange = {
                hobbyText.value = it
                signUpViewModel.onHobbyChange(it)
                isHobbyValid = StringInputValidCheck(it)
            },
            fieldType = "hobby",
            conditionCheck = isHobbyValid,
            errMessage = "취미은 7자 이하여야 합니다.",
            placeholder = "취미 입력",
        )

        Spacer(modifier = Modifier.weight(0.5f))
        SocialLoginSection(modifier = modifier)
        Spacer(modifier = Modifier.weight(1f))

        Text(
            "Wavve 회원가입",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(vertical = 13.dp)
                .clickable {

                    /*Todo: 클릭 시 조건 검사 및 토스트 띄우는 것까지 createNewUser 안에 넣기*/

                    //유저 네임 형식 조건 검사
                    if (!StringInputValidCheck(userNameText.value)) {
                        toastMessage = "형식에 맞는 유저 네임을 입력하세요"

                    }
                    //비밀번호 형식 조건 검사
                    if (!PasswordValidCheck(passwordText.value)) {
                        toastMessage = "조건에 맞는 비밀번호를 사용하세요"
                    }

                    if (signUpViewModel.isSignUpValid()) {

                        coroutineScope.launch {
                            signUpViewModel.createNewUser()
                        }

                        toastMessage = "회원가입에 성공하였습니다."

                        navigateToLoginScreen(User(userNameText.value, passwordText.value))
                    }

                    Toast
                        .makeText(context, toastMessage, Toast.LENGTH_SHORT)
                        .show()

                },
            color = Color.White
        )
    }
}



@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    ANDANDROIDTheme {
        SignUpScreen(
        )
    }
}