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

                    Greeting2(
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
fun Greeting2(modifier: Modifier = Modifier,
              scope : CoroutineScope,
              snackbarHostState: SnackbarHostState
) {

    val context = LocalContext.current
    val activity = context as? ComponentActivity

    var mypageIntent = Intent(context, MyActivity::class.java)

    var shouldShowPassword = remember {mutableStateOf(false)}
    var shouldDisplayShow = remember {mutableStateOf(true)} //0이면 show 보이기, 1이면 hide 보이기




    val deliveredEmail = activity?.intent?.getStringExtra("email") ?: ""
    val deliveredPassword = activity?.intent?.getStringExtra("password") ?: ""

    Log.d("LoginActivity", "Received Email: $deliveredEmail")
    Log.d("LoginActivity", "Received Password: $deliveredPassword")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1B1B))
            .padding(25.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
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

        var EmailText = remember { mutableStateOf("") }
        var PasswordText = remember { mutableStateOf("") }
        var PasswordFieldButtonMessage = ""


        TextField(
            value = EmailText.value,
            onValueChange = { EmailText.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("이메일 주소 또는 아이디") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedTextColor = Color.DarkGray
            ),
        )
        Spacer(modifier = Modifier.weight(0.025f))
        TextField(
            value = PasswordText.value,
            onValueChange = { PasswordText.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("비밀번호") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedTextColor = Color.DarkGray
            ),
            trailingIcon = {
                if(shouldDisplayShow.value == true){
                    PasswordFieldButtonMessage = "show"
                }else{
                    PasswordFieldButtonMessage = "hide"
                }
                Text(
                    text = PasswordFieldButtonMessage,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable{
                        shouldShowPassword.value = !shouldShowPassword.value
                        shouldDisplayShow.value = !shouldDisplayShow.value
                    }
                )
            },
            visualTransformation = if(shouldShowPassword.value){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            }
        )
        Spacer(modifier = Modifier.weight(0.2f))

        Button(
            onClick = {

                var loginMessage = ""
                var loginSuccessFlag = 0;


                if(EmailText.value == deliveredEmail && PasswordText.value == deliveredPassword){
                    loginMessage = "로그인 성공"
                    loginSuccessFlag = 1;
                } else {
                    loginMessage = "알맞은 이메일과 비밀번호를 입력하세요"
                }

                scope.launch {
                    val snackbarResult = snackbarHostState.showSnackbar(loginMessage)

                    if(loginSuccessFlag == 1 && snackbarResult == SnackbarResult.Dismissed){
                        mypageIntent.apply {
                            putExtra("email", EmailText.value)
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(this)
                        }
                    }
                }
                //Toast.makeText(context, loginMessage, Toast.LENGTH_SHORT).show()



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
            Text("아이콘", color = Color.Gray)
            Text("아이콘", color = Color.Gray)
            Text("아이콘", color = Color.Gray)
            Text("아이콘", color = Color.Gray)
            Text("아이콘", color = Color.Gray)
            Spacer(modifier = Modifier.width(10.dp))

        }
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            "* SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다.\n " +
                    "기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요.",
            color = Color.Gray,
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.weight(1f))

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ANDANDROIDTheme {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        Greeting2(
            scope = scope,
            snackbarHostState = snackbarHostState
        )
    }
}