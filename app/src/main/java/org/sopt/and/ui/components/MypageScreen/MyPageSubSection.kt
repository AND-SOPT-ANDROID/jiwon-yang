package org.sopt.and.ui.components.MypageScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyPageSubSection(
    modifier: Modifier = Modifier,
    title: String,
    topic: String,
    contentNumber: Int
){
    if(contentNumber == 0){ //비어있는 경우 경고 메시지
        Column(
            modifier = modifier,
        ){
            Text(
                "$title",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Icon(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(8.dp),
                    imageVector = Icons.Default.Warning,
                    contentDescription = "blank Icon",
                    tint = Color.LightGray
                )
                Text(
                    "${topic}이 없어요.",
                    color = Color.Gray,
                )
            }
        }
    } else {
        /*0개가 아니면 리스트뷰로 보여주기*/
    }
}
