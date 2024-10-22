package org.sopt.and.ui.components.MypageScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyPageProfileSection2(
    sectionDescription: String,
    connectedUrl: String            /*  나중에 화면 전환할 때 쓰이지 않을까 싶어서 ....*/
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(vertical = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                "$sectionDescription",
                color = Color.Gray
            )
            Text(
                "구매하기 >",
                color = Color.White
            )
        }
    }

}