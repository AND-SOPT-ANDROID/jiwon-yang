package org.sopt.and

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable
import org.sopt.and.ui.components.SignUpandLogIn.SignUpTextField
import org.sopt.and.ui.components.SignUpandLogIn.SocialLoginSection
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Serializable
data object SignUpScreen

fun EmailValidCheck(email: String): Boolean {
    var isValid = false
    //val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val inputStr : CharSequence = email
    val pattern = Patterns.EMAIL_ADDRESS
    val matcher = pattern.matcher(inputStr)
    if(matcher.matches()){
        isValid = true
    }
    return isValid
}

fun PasswordValidCheck(password: String): Boolean {
    val pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)|(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*])|(?=.*[a-z])(?=.*\\d)(?=.*[!@#\$%^&*])|(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{8,20}\$".toRegex()
    return password.matches(pattern)

}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToLoginScreen: (emailText: String, passwordText: String) -> Unit,
) {

    val context = LocalContext.current

    var emailFlag = 0
    var passwordFlag = 0 //8~20자 이내 조건 확인
    var toastMessage = ""

    var emailText = remember { mutableStateOf("") }
    var passwordText = remember { mutableStateOf("") }
    var PasswordFieldButtonMessage = ""

    var shouldShowPassword = remember {mutableStateOf(false)}
    var shouldDisplayShow = remember {mutableStateOf(true)} //0이면 show 보이기, 1이면 hide 보이기
    var isEmailValid = remember { mutableStateOf(true) }
    var isPasswordValid = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1B1B))
            .padding(25.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
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
            "이메일과 비밀번호만으로\nWavve를 즐길 수 있어요!",
            color = Color.White,
            fontSize = 21.sp
        )

        Spacer(modifier = Modifier.weight(0.25f))

        SignUpTextField(
            text = emailText.value,
            onValueChange = { newValue ->
                emailText.value = newValue
                isEmailValid.value = EmailValidCheck(emailText.value)
            },
            fieldType = "Email",
            conditionCheck = isEmailValid.value,
            errMessage = "올바른 이메일 형식이 아닙니다.",
            placeholder = "wavve@example.com",
            descriptionText = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해주세요.",
        )

        Spacer(modifier = Modifier.weight(0.15f))

        SignUpTextField(
            text = passwordText.value,
            onValueChange = { newValue ->
                passwordText.value = newValue
                isPasswordValid.value = PasswordValidCheck(passwordText.value)
            },
            fieldType = "Password",
            conditionCheck = isPasswordValid.value,
            errMessage = "올바른 비밀번호 형식이 아닙니다.",
            placeholder = "Wavve 비밀번호 설정",
            shouldShowPassword = shouldShowPassword.value,
            onPasswordVisibilityChange = {
                shouldShowPassword.value = !shouldShowPassword.value
            },
            descriptionText = "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.",
        )

        Spacer(modifier = Modifier.weight(0.5f))
        SocialLoginSection(modifier = modifier)
        Spacer(modifier = Modifier.weight(1f))

        //var intent = Intent(context, LoginActivity::class.java)

        Text(
            "Wavve 회원가입",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(vertical = 13.dp)
                .clickable {

                    //이메일 형식 조건 검사
                    if (!EmailValidCheck(emailText.value)) {
                        emailFlag = 1
                        toastMessage = "형식에 맞는 이메일을 입력하세요"

                    }

                    //비밀번호 형식 조건 검사
                    if (!PasswordValidCheck(passwordText.value)) {
                        passwordFlag = 1
                        toastMessage = "조건에 맞는 비밀번호를 사용하세요"
                    }

                    if (emailFlag == 0 && passwordFlag == 0) {
//                        intent.putExtra("email", emailText.value)
//                        intent.putExtra("password", passwordText.value)
//                        intent.apply {
//                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                            context.startActivity(this)
//                        }

                        toastMessage = "로그인 되었습니다"

                        //전달해줄 인자를 이 안에 넣으면 되는 듯..
                        navigateToLoginScreen(emailText.value, passwordText.value)
                        println("네비게이트는 지남...")
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
                email, password ->
                println("email: $email, password: $password")
            }
        )
    }
}