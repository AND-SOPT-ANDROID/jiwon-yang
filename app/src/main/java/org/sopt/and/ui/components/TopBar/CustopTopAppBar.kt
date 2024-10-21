@file:OptIn(ExperimentalMaterial3Api::class)

package org.sopt.and.ui.components.TopBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.sopt.and.R

@Composable
fun CustomTopAppBar(navController: NavController){
    TopAppBar(
        title = { },
        colors = topAppBarColors(
            containerColor = Color(0xFF1B1B1B),
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.wavve_logo),
                contentDescription = "Wavve Logo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(start = 16.dp),
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp),
                imageVector = Icons.Rounded.ExitToApp,
                contentDescription = "to the TV"
            )
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp),
                imageVector = Icons.Rounded.MailOutline,
                contentDescription = "Live 방송"
            )

        }
    )
}