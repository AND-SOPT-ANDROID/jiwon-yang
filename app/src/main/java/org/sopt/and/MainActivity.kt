package org.sopt.and

import android.R
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.core.content.ContextCompat.startActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    //private lateinit var signInLauncher: ActivityResultLauncher<Intent>

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
    val pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)|(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*])|(?=.*[a-z])(?=.*\\d)(?=.*[!@#\$%^&*])|(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{8,20}\$".toRegex()
    return password.matches(pattern)

}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    
    //이메일, 비밀번호 유효 여부에 따른 회원가입 가능여부 flag
    var email_flag = 0;
    var password_flag = 0; //8~20자 이내 조건 확인
    var toastMessage = "";

    var shouldShowPassword = remember {mutableStateOf(false)}
    var shouldDisplayShow = remember {mutableStateOf(true)} //0이면 show 보이기, 1이면 hide 보이기


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
        var PasswordFieldButtonMessage = ""



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
            placeholder = { Text("Wavve 비밀번호 설정") },
            visualTransformation = if(shouldShowPassword.value){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            }
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
                        toastMessage = "형식에 맞는 이메일을 입력하세요"

                    }

                    //비밀번호 형식 조건 검사
                    if(!PasswordValidCheck(PasswordText.value)){
                        password_flag = 1;
                        toastMessage = "조건에 맞는 비밀번호를 사용하세요"
                    }

                    if(email_flag == 0 && password_flag == 0){


                        intent.putExtra("email", EmailText.value)
                        intent.putExtra("password", PasswordText.value)

                        toastMessage = "로그인 되었습니다"
                        intent.apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(this)
                        }
                    }

                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()

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