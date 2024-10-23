@file:OptIn(ExperimentalMaterial3Api::class)

package org.sopt.and.ui.components.TopBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.components.SignUpandLogIn.SocialLoginSection

@Composable
fun CustomTopAppBarSecond(navController: NavController){
    TopAppBar(
        title = { },
        colors = topAppBarColors(
            containerColor = Color(0xFF1B1B1B),
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Text("뉴클래식", color = Color.LightGray)
                Text("드라마", color = Color.LightGray)
                Text("예능", color = Color.LightGray)
                Text("영화", color = Color.LightGray)
                Text("애니", color = Color.LightGray)
                Text("해외시리즈", color = Color.LightGray)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarSecondPreview(){
    val navController = rememberNavController()
    CustomTopAppBarSecond(navController = navController)
}