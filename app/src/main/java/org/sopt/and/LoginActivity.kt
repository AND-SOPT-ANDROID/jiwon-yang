package org.sopt.and

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.and.ui.components.SignUpTextField
import org.sopt.and.ui.theme.ANDANDROIDTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->

                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        scope = scope,
                        snackbarHostState = snackbarHostState
                    )

                }
            }
        }
    }

}

@Composable
fun LoginScreen(modifier: Modifier = Modifier,
              scope : CoroutineScope,
              snackbarHostState: SnackbarHostState
) {

    val context = LocalContext.current
    val activity = context as? ComponentActivity

    var mypageIntent = Intent(context, MyActivity::class.java)

    var emailText = remember { mutableStateOf("") }
    var passwordText = remember { mutableStateOf("") }

    var isEmailValid = remember { mutableStateOf(true) }
    var isPasswordValid = remember { mutableStateOf(true) }

    var shouldShowPassword = remember {mutableStateOf(false)}

    val deliveredEmail = activity?.intent?.getStringExtra("email") ?: ""
    val deliveredPassword = activity?.intent?.getStringExtra("password") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1B1B))
            .padding(25.dp),
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Wavve",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Color.White

        )
        Spacer(modifier = Modifier.weight(0.3f))

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
        )
        Spacer(modifier = Modifier.weight(0.025f))
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
        )
        Spacer(modifier = Modifier.weight(0.2f))

        Button(
            onClick = {

                var loginMessage = ""
                var loginSuccessFlag = 0


                if(emailText.value == deliveredEmail && passwordText.value == deliveredPassword){
                    loginMessage = "로그인 성공"
                    loginSuccessFlag = 1
                } else {
                    loginMessage = "알맞은 이메일과 비밀번호를 입력하세요"
                }

                scope.launch {
                    val snackbarResult = snackbarHostState.showSnackbar(loginMessage)

                    if(loginSuccessFlag == 1 && snackbarResult == SnackbarResult.Dismissed){
                        mypageIntent.apply {
                            putExtra("email", emailText.value)
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(this)
                        }
                    }
                }



            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                "로그인",
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp))
        }
        Spacer(modifier = Modifier.weight(0.2f))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ){
            Text(
                "아이디 찾기",
                color = Color.Gray,
                fontSize = 13.sp
            )
            Text(
                " | ",
                color = Color.Gray,
                fontSize = 13.sp
            )
            Text(
                "비밀번호 재설정",
                color = Color.Gray,
                fontSize = 13.sp
            )
            Text(
                " | ",
                color = Color.Gray,
                fontSize = 13.sp
            )
            Text(
                "회원가입",
                color = Color.Gray,
                fontSize = 13.sp
            )
        }
        Spacer(modifier = Modifier.weight(0.2f))
        Text(
            "또는 다른 서비스 계정으로 가입",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Spacer(modifier = Modifier.width(10.dp))
            repeat(5) { Text("아이콘", color = Color.Gray) } //kotlin에서의 반복문 활용법
            Spacer(modifier = Modifier.width(10.dp))

        }
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            "* SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다.\n기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요.",
            color = Color.Gray,
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.weight(1f))

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
            snackbarHostState = snackbarHostState
        )
    }
}