package org.sopt.and

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.components.BottomBar.CustomBottomAppBar
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            CustomBottomAppBar(navController = navController)
        }
    ) { innerPadding ->
        Text(
            text = "검색 페이지\n입니다........................\n..........",
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xFF1B1B1B))
                .fillMaxSize(),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    val navController = rememberNavController()

    ANDANDROIDTheme {
        SearchScreen(
            navController = navController
        )
    }
}
