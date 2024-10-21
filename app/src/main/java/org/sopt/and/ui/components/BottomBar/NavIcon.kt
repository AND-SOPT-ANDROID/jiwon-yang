package org.sopt.and.ui.components.BottomBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavIcon(
    navController: NavController,
    route: String,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String
){
    Column(
        modifier = modifier.clickable {navController.navigate(route)},
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ){
        if(text != "MY"){
            Icon(modifier = Modifier.size(24.dp), imageVector = icon, contentDescription = text)
            Text(text = text)
        } else {
            Text(text = "(사진)")
            Text(text = text)
        }

    }
}