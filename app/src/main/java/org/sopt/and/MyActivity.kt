package org.sopt.and

import android.content.Intent
import android.graphics.Paint
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1B1B))
            //.padding(25.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(vertical = 30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                Text(
                    "프로필 1님",
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(0.5f))
                Text(
                    "아이콘",
                    color = Color.White
                )
                Text(
                    "아이콘",
                    color = Color.White
                )
            }


        }
        Spacer(modifier = Modifier.height(0.5.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(vertical = 1.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                Text(
                    "첫 결제 시 첫 달 100원!",
                    color = Color.Gray
                )
                Text(
                    "구매하기 >",
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(0.5.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(vertical = 1.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                Text(
                    "현재 보유하신 이용권이 없습니다.",
                    color = Color.Gray
                )
                Text(
                    "구매하기 >",
                    color = Color.White
                )
            }
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Text(
                "전체 시청내역",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    "아이콘",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(30.dp),
                )
                Text(
                    "시청내역이 없어요.",
                    color = Color.Gray,
                )
            }
        }
        Spacer(
            modifier = Modifier.weight(0.2f)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Text(
                "관심 프로그램",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    "아이콘",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(30.dp),
                )
                Text(
                    "관심 프로그램이 없어요.",
                    color = Color.Gray,
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ANDANDROIDTheme {
        Greeting3()
    }
}