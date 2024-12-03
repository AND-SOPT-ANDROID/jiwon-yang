package org.sopt.and.ui.components.SignUpandLogIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun SocialLoginSection(
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.fillMaxWidth(),
    ){
        Text(
            "또는 다른 서비스 계정으로 가입",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.btn_kakao),
                contentDescription = "Kakao Logo",
                contentScale = ContentScale.Fit
            )
            Image(
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.btn_gmail),
                contentDescription = "Gmail Logo",
                contentScale = ContentScale.Fit
            )
            Image(
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.btn_naver),
                contentDescription = "Naver Logo",
                contentScale = ContentScale.Fit
            )
            Image(
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.btn_google),
                contentDescription = "Google Logo",
                contentScale = ContentScale.Fit
            )
            Image(
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.btn_apple),
                contentDescription = "Apple Logo",
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(10.dp))

        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            "* SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다. 기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요.",
            color = Color.Gray,
            fontSize = 12.sp
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSocialLogin(){
    SocialLoginSection()
}