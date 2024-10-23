package org.sopt.and

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.sopt.and.ui.components.SignUpandLogIn.SignUpTextField
import org.sopt.and.ui.components.SignUpandLogIn.SocialLoginSection
import org.sopt.and.ui.theme.ANDANDROIDTheme


@Serializable
data class LoginScreen(
    val emailText: String,
    val passwordText: String
)

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    emailText: String,
    passwordText: String,
    navigateToHomeScreen: () -> Unit,
    userViewModel: UserViewModel = viewModel()
) {

    var inputEmail: String = ""
    var inputPassword: String = ""

    var emailState = remember { mutableStateOf(inputEmail) }
    var passwordState = remember { mutableStateOf(inputPassword) }

    var isEmailValid = remember { mutableStateOf(true) }
    var isPasswordValid = remember { mutableStateOf(true) }

    var shouldShowPassword = remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1B1B1B))
                .padding(innerPadding)
                .padding(25.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.wavve_logo),
                    contentDescription = "Wavve Logo",
                    modifier = Modifier.size(100.dp)
                )
            }

            // Email 입력 필드
            SignUpTextField(
                text = emailState.value,
                onValueChange = { newValue ->
                    emailState.value = newValue
                    isEmailValid.value = EmailValidCheck(emailState.value)
                },
                fieldType = "Email",
                conditionCheck = isEmailValid.value,
                errMessage = "올바른 이메일 형식이 아닙니다.",
                placeholder = "wavve@example.com",
            )

            Spacer(modifier = Modifier.weight(0.025f))

            // Password 입력 필드
            SignUpTextField(
                text = passwordState.value,
                onValueChange = { newValue ->
                    passwordState.value = newValue
                    isPasswordValid.value = PasswordValidCheck(passwordState.value)
                },
                fieldType = "Password",
                conditionCheck = isPasswordValid.value,
                errMessage = "올바른 비밀번호 형식이 아닙니다.",
                placeholder = "Wavve 비밀번호 설정",
                shouldShowPassword = shouldShowPassword.value,
                onPasswordVisibilityChange = {
                    shouldShowPassword.value = !shouldShowPassword.value
                },
            )

            Spacer(modifier = Modifier.weight(0.2f))

            // 로그인 버튼
            Button(
                onClick = {
                    var loginMessage = ""
                    var loginSuccessFlag = 0

                    if (emailState.value == emailText && passwordState.value == passwordText) {
                        loginMessage = "로그인 성공"
                        loginSuccessFlag = 1
                    } else {
                        loginMessage = "알맞은 이메일과 비밀번호를 입력하세요"
                    }

                    scope.launch {
                        val snackbarResult = snackbarHostState.showSnackbar(loginMessage)

                        if (loginSuccessFlag == 1 && snackbarResult == SnackbarResult.Dismissed) {
                            userViewModel.setEmail(emailState.value)
                            navigateToHomeScreen()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("로그인", color = Color.White, modifier = Modifier.padding(vertical = 8.dp))
            }

            Spacer(modifier = Modifier.weight(0.2f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("아이디 찾기", color = Color.Gray, fontSize = 13.sp)
                Text(" | ", color = Color.Gray, fontSize = 13.sp)
                Text("비밀번호 재설정", color = Color.Gray, fontSize = 13.sp)
                Text(" | ", color = Color.Gray, fontSize = 13.sp)
                Text("회원가입", color = Color.Gray, fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.weight(0.2f))

            // 소셜 로그인 섹션
            SocialLoginSection(modifier = modifier)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview2() {
    ANDANDROIDTheme {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        LoginScreen(
            scope = scope,
            snackbarHostState = snackbarHostState,
            emailText = "",
            passwordText = "",
            navigateToHomeScreen = {},
        )
    }
}