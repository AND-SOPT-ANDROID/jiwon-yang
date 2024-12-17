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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.User
import org.sopt.and.ui.components.SignUpandLogIn.SignUpTextField
import org.sopt.and.ui.components.SignUpandLogIn.SocialLoginSection
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToLoginScreen: () -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by signUpViewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // SideEffect 감지
    LaunchedEffect(Unit) {
        signUpViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SignUpContract.SignUpSideEffect.ShowSuccessToast -> {
                    Toast.makeText(context, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                }
                is SignUpContract.SignUpSideEffect.ShowErrorToast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
                is SignUpContract.SignUpSideEffect.NavigateToLoginScreen -> {
                    navigateToLoginScreen()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1B1B))
            .padding(25.dp)
    ) {
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
            text = uiState.userName,
            onValueChange = { signUpViewModel.setEvent(SignUpContract.SignUpEvent.OnUserNameChanged(it)) },
            fieldType = "Username",
            conditionCheck = uiState.isUserNameValid,
            errMessage = "유저 이름은 7자 이하여야 합니다.",
            placeholder = "유저 이름 (7자 이하)",
            descriptionText = "로그인, 비밀번호 찾기, 알림에 사용되니 정확하게 입력해주세요."
        )
        Spacer(modifier = Modifier.weight(0.15f))

        SignUpTextField(
            text = uiState.password,
            onValueChange = { signUpViewModel.setEvent(SignUpContract.SignUpEvent.OnPasswordChanged(it)) },
            fieldType = "Password",
            conditionCheck = uiState.isPasswordValid,
            errMessage = "비밀번호는 8~20자 영문 대소문자, 숫자, 특수문자를 포함해야 합니다.",
            placeholder = "비밀번호 입력",
            shouldShowPassword = uiState.shouldShowPassword,
            onPasswordVisibilityChange = {
                signUpViewModel.setEvent(SignUpContract.SignUpEvent.OnTogglePasswordVisibility)
            },
            descriptionText = "비밀번호는 8~20자 이내로 영문, 숫자, 특수문자 중 3가지 이상 혼용해주세요."
        )
        SignUpTextField(
            text = uiState.hobby,
            onValueChange = { signUpViewModel.setEvent(SignUpContract.SignUpEvent.OnHobbyChanged(it)) },
            fieldType = "Hobby",
            conditionCheck = uiState.isHobbyValid,
            errMessage = "취미는 7자 이하여야 합니다.",
            placeholder = "취미 입력"
        )

        Spacer(modifier = Modifier.weight(0.5f))
        SocialLoginSection(modifier = modifier)
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Wavve 회원가입",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(13.dp)
                .clickable { signUpViewModel.setEvent(SignUpContract.SignUpEvent.OnSignUpButtonClicked) },
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(
        navigateToLoginScreen = { } // 더미 함수 전달
    )
}
