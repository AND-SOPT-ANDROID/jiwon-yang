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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.R
import org.sopt.and.ui.components.SignUpandLogIn.SignUpTextField
import org.sopt.and.ui.components.SignUpandLogIn.SocialLoginSection

@Composable
fun LoginScreen(
    navigateToHomeScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel() //뷰모델을 직접 넣어주는게 아닌듯. 하나하나 함수별로 넣어주는 듯
) {
    val uiState = loginViewModel.uiState.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        loginViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                LoginContract.LoginSideEffect.NavigateToHome -> navigateToHomeScreen()
                is LoginContract.LoginSideEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(sideEffect.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1B1B1B))
                .padding(innerPadding)
                .padding(25.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.wavve_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            SignUpTextField(
                text = uiState.userName,
                onValueChange = { loginViewModel.setEvent(LoginContract.LoginEvent.OnUserNameChanged(it)) },
                fieldType = "UserName",
                conditionCheck = uiState.isUserNameValid,
                placeholder = "유저 이름 (7자 이하)",
                errMessage = "유저 이름은 7자 이하여야 합니다."
            )

            Spacer(modifier = Modifier.height(8.dp))

            SignUpTextField(
                text = uiState.password,
                onValueChange = { loginViewModel.setEvent(LoginContract.LoginEvent.OnPasswordChanged(it)) },
                fieldType = "Password",
                conditionCheck = uiState.isPasswordValid,
                placeholder = "비밀번호 입력",
                errMessage = "비밀번호는 8자 이내여야 합니다.",
                shouldShowPassword = uiState.shouldShowPassword,
                onPasswordVisibilityChange = {
                    loginViewModel.setEvent(LoginContract.LoginEvent.OnTogglePasswordVisibility)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { loginViewModel.setEvent(LoginContract.LoginEvent.OnLoginButtonClicked) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("로그인", color = Color.White)
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
            SocialLoginSection(modifier = Modifier)
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navigateToHomeScreen = {})
}
