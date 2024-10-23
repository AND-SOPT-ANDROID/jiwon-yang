package org.sopt.and.ui.components.BottomBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun CustomBottomAppBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color.Black,
        contentColor = Color.White,

        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            NavIcon(
                navController = navController,
                route = "home",
                icon = Icons.Filled.Home,
                text = "홈"
            )
            NavIcon(
                navController = navController,
                route = "search",
                icon = Icons.Filled.Search,
                text = "검색"
            )
            NavIcon(
                navController = navController,
                route = "profile",
                icon = Icons.Filled.Person,
                text = "MY"
            )
        }
    }
}