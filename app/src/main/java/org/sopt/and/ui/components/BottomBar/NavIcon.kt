package org.sopt.and.ui.components.BottomBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.sopt.and.R

@Composable
fun NavIcon(
    navController: NavController,
    navBarViewModel: NavBarViewModel = hiltViewModel(),
    route: String,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    pageIndex: Int
){
    val uiState by navBarViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.clickable {
            navBarViewModel.setEvent(NavBarContract.NavBarEvent.OnPageSelected(pageIndex))
            when (route) {
                "home" -> {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
                "search" -> {
                    navController.navigate("search") {
                        popUpTo("search") { inclusive = true }
                    }
                }
                "profile" -> {
                    navController.navigate("mypage") {
                        popUpTo("mypage") { inclusive = true }
                    }
                }
            }
        },
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ){
        if(text != "MY"){
            Icon(modifier = Modifier.size(24.dp), imageVector = icon, contentDescription = text)
            Text(text = text)
        } else {
            Image(
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.profile_img),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Fit
            )
            Text(text = text)
        }

    }
}