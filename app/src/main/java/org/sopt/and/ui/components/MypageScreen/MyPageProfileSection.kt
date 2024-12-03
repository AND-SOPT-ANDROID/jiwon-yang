package org.sopt.and.ui.components.MypageScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun MyPageProfileSection(
    deliveredEmail: String,
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(70.dp, 70.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.profile_img),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "${deliveredEmail}님",
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "notifications",
                tint = Color.LightGray
            )
            Spacer(modifier = Modifier.width(30.dp))
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "settings",
                tint = Color.LightGray
            )
        }
    }
}