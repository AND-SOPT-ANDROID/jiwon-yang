package org.sopt.and

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(modifier: Modifier = Modifier) {

    val context = LocalContext.current

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

        TextField(
            value = EmailText.value,
            onValueChange = { EmailText.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("이메일 주소 또는 아이디") },
            singleLine = true,
        )
        Spacer(modifier = Modifier.weight(0.025f))
        TextField(
            value = PasswordText.value,
            onValueChange = { PasswordText.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("비밀번호") },
            singleLine = true,
        )
        Spacer(modifier = Modifier.weight(0.2f))
        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue),

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
                "1번 텍스트",
                color = Color.Gray,
                fontSize = 13.sp
            )
            Text(
                " | ",
                color = Color.Gray,
                fontSize = 13.sp
            )
            Text(
                "1번 텍스트",
                color = Color.Gray,
                fontSize = 13.sp
            )
            Text(
                " | ",
                color = Color.Gray,
                fontSize = 13.sp
            )
            Text(
                "1번 텍스트",
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
        /*아이콘 배치하기*/
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
        Greeting2()
    }
}