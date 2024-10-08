package org.sopt.and

import android.R
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

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
    var isValid = false
    val expression = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$" // 영문, 숫자, 특수문자
    val inputStr : CharSequence = password
    //val matcher = expression.matcher(intputStr)
    return isValid
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    
    //이메일, 비밀번호 유효 여부에 따른 회원가입 가능여부 flag
    var email_flag = 0;
    var password_flag = 0; //8~20자 이내 조건 확인
    var password_flag2 = 0; //영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용 여부 확인


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

        var EmailText = remember { mutableStateOf("") }
        var PasswordText = remember { mutableStateOf("") }

        Spacer(modifier = Modifier.weight(0.25f))
        TextField(
            value = EmailText.value,
            onValueChange = { EmailText.value = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedTextColor = Color.DarkGray
            ),
            placeholder = { Text("wavve@example.com") },
            singleLine = true,

        )
        Spacer(modifier = Modifier.weight(0.025f))
        Text(
            "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해주세요.",
            color = Color.Gray,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.weight(0.15f))
        TextField(
            value = PasswordText.value,
            onValueChange = { PasswordText.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("Wavve 비밀번호 설정") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedTextColor = Color.DarkGray
            ),
        )
        Spacer(modifier = Modifier.weight(0.025f))
        Text(
            "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.",
            color = Color.Gray,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.weight(0.5f))
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
            "* SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다. 기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요.",
            color = Color.Gray,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        var intent = Intent(context, LoginActivity::class.java)
        Text(
            "Wavve 회원가입",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(vertical = 13.dp)
                .clickable{

                    //이메일 형식 조건 검사
                    if(!EmailValidCheck(EmailText.value)){
                        email_flag = 1;
                    }

                    if(PasswordText.value.length < 8 || PasswordText.value.length > 20){
                        password_flag = 1;
                    }

                    //비밀번호 형식 조건 검사
                    if(PasswordText.value.length <= 6){
                        password_flag2 = 1;
                    }


                    Toast.makeText(context, "로그인 되었습니다", Toast.LENGTH_SHORT).show()
                    intent.apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        context.startActivity(this)
                    }
                },
            color = Color.White
        )


    }



}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ANDANDROIDTheme {
        Greeting()
    }
}