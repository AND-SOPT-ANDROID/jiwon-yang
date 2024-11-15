package org.sopt.and.presentation.signupScreen

import android.util.Patterns
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import org.sopt.and.model.dto.RequestCreateUserDto
import org.sopt.and.ui.components.SignUpandLogIn.SignUpTextField
import org.sopt.and.ui.components.SignUpandLogIn.SocialLoginSection
import org.sopt.and.ui.theme.ANDANDROIDTheme
import kotlin.math.sign

@Serializable
data object SignUpScreen

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
    val pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)|(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*])|(?=.*[a-z])(?=.*\\d)(?=.*[!@#\$%^&*])|(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{8,20}\$".toRegex()
    return password.matches(pattern)

}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToLoginScreen: (userNameText: String, passwordText: String) -> Unit,
    signUpViewModel: SignUpViewModel = viewModel()
) {

    val context = LocalContext.current
    var toastMessage = ""

    var userNameText = signUpViewModel.userNameText.collectAsState().value
    var passwordText = signUpViewModel.passwordText.collectAsState().value
    var hobbyText = signUpViewModel.hobbyText.collectAsState().value
    var isUserNameValid = signUpViewModel.isUserNameValid.collectAsState().value
    var isPasswordValid = signUpViewModel.isPasswordValid.collectAsState().value
    var isHobbyValid = signUpViewModel.isHobbyValid.collectAsState().value
    var shouldShowPassword = signUpViewModel.shouldShowPassword.collectAsState().value

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
            text = userNameText,
            onValueChange = { newValue ->
                signUpViewModel.onUserNameChange(newValue)
                isUserNameValid = StringInputValidCheck(userNameText)
            },
            fieldType = "userName",
            conditionCheck = isUserNameValid,
            errMessage = "유저 이름은 7자 이하여야 합니다.",
            placeholder = "유저 이름 (7자 이하)",
            descriptionText = "로그인, 비밀번호 찾기, 알림에 사용되니 정확하게 입력해주세요.",
        )

        Spacer(modifier = Modifier.weight(0.15f))

        SignUpTextField(
            text = passwordText,
            onValueChange = { newValue ->
                signUpViewModel.onPasswordChange(newValue)
                isPasswordValid = PasswordValidCheck(passwordText)
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
            text = hobbyText,
            onValueChange = { newValue ->
                signUpViewModel.onHobbyChange(newValue)
                isHobbyValid = StringInputValidCheck(hobbyText)
            },
            fieldType = "hobby",
            conditionCheck = isHobbyValid,
            errMessage = "취미은 7자 이하여야 합니다.",
            placeholder = "취미 입력",
            //descriptionText = "로그인, 비밀번호 찾기, 알림에 사용되니 정확하게 입력해주세요.",
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

                    //유저 네임 형식 조건 검사
                    if (!StringInputValidCheck(userNameText)) {
                        toastMessage = "형식에 맞는 유저 네임을 입력하세요"

                    }

                    //비밀번호 형식 조건 검사
                    if (!PasswordValidCheck(passwordText)) {
                        toastMessage = "조건에 맞는 비밀번호를 사용하세요"
                    }

                    if (signUpViewModel.isSignUpValid()) {

                        /* 새로 회원가입한 정보 저장 */
                        val newUser = RequestCreateUserDto(
                            userName = userNameText,
                            password = passwordText,
                            hobby = hobbyText
                        )
                        signUpViewModel.createNewUser(newUser)

                        toastMessage = "회원가입에 성공하였습니다."


                        navigateToLoginScreen(userNameText, passwordText)
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
            navigateToLoginScreen = {
                userName, password ->
                println("userName: $userName, password: $password")
            }
        )
    }
}