package org.sopt.and.presentation.loginScreen

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.sopt.and.presentation.signupScreen.StringInputValidCheck
import org.sopt.and.presentation.signupScreen.PasswordValidCheck
import org.sopt.and.R
import org.sopt.and.presentation.main.UserViewModel
import org.sopt.and.ui.components.SignUpandLogIn.SignUpTextField
import org.sopt.and.ui.components.SignUpandLogIn.SocialLoginSection
import org.sopt.and.ui.theme.ANDANDROIDTheme


@Serializable
data class LoginScreen(
    val userNameText: String,
    val passwordText: String
)

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    userNameText: String,
    passwordText: String,
    navigateToHomeScreen: () -> Unit,
    userViewModel: UserViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel()
) {

    var userNameState = loginViewModel.userNameState.collectAsState().value
    var passwordState = loginViewModel.passwordState.collectAsState().value
    var isUserNameValid = loginViewModel.isUserNameValid.collectAsState().value
    var isPasswordValid = loginViewModel.isPasswordValid.collectAsState().value
    var shouldShowPassword = loginViewModel.shouldShowPassword.collectAsState().value

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

            // UserName 입력 필드
            SignUpTextField(
                text = userNameState,
                onValueChange = { newValue ->
                    loginViewModel.onUserNameChange(newValue)
                    isUserNameValid = StringInputValidCheck(userNameState)
                },
                fieldType = "UserName",
                conditionCheck = isUserNameValid,
                errMessage = "유저 이름은 7자 이하여야 합니다.",
                placeholder = "유저 이름 (7자 이하)",
            )

            Spacer(modifier = Modifier.weight(0.025f))

            // Password 입력 필드
            SignUpTextField(
                text = passwordState,
                onValueChange = { newValue ->
                    loginViewModel.onPasswordChange(newValue)
                    isPasswordValid = PasswordValidCheck(passwordState)
                },
                fieldType = "Password",
                conditionCheck = isPasswordValid,
                errMessage = "올바른 비밀번호 형식이 아닙니다.",
                placeholder = "Wavve 비밀번호 설정",
                shouldShowPassword = shouldShowPassword,
                onPasswordVisibilityChange = {
//                    shouldShowPassword = !shouldShowPassword
                    loginViewModel.togglePasswordVisibility()
                },
            )

            Spacer(modifier = Modifier.weight(0.2f))

            // 로그인 버튼
            Button(
                onClick = {
                    var loginMessage = ""
                    var loginSuccessFlag = 0

                    if (loginViewModel.isLoginValid(userNameText, passwordText)) {
                        loginMessage = "로그인 성공"
                        loginSuccessFlag = 1

                        /* TODO: 로그인 성공한 유저네임 갖고, 저장된 유저의 정보 가져오기.
                        *   로그인 성공 시, response로 돌아오는 token 값을 넘겨주기 */

                        userViewModel.setUserName(userNameText)


                    } else {
                        loginMessage = "알맞은 유저 이름과 비밀번호를 입력하세요"
                    }

                    scope.launch {
                        val snackbarResult = snackbarHostState.showSnackbar(loginMessage)

                        if (loginSuccessFlag == 1 && snackbarResult == SnackbarResult.Dismissed) {
                            userViewModel.setUserName(userNameState)
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
            userNameText = "",
            passwordText = "",
            navigateToHomeScreen = {},
        )
    }
}