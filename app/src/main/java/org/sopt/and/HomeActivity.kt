@file:OptIn(ExperimentalMaterial3Api::class)

package org.sopt.and

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.serialization.Serializable
import org.sopt.and.ui.components.BottomBar.CustomBottomAppBar
import org.sopt.and.ui.components.BottomBar.NavIcon
import org.sopt.and.ui.components.HomeScreen.HomeLazyRow
import org.sopt.and.ui.components.TopBar.CustomTopAppBar
import org.sopt.and.ui.components.TopBar.CustomTopAppBarSecond
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Serializable
data object HomeScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController, // navController를 넘겨 받아 사용
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(navController = navController)
                CustomTopAppBarSecond(navController = navController)
            }
        },
        bottomBar = {
            CustomBottomAppBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color(0xFF1B1B1B))
                .padding(innerPadding)  // 패딩 적용
                .padding(all = 10.dp)
        ) {

            val images = listOf(
                R.drawable.food_pic1,
                R.drawable.food_pic2,
                R.drawable.food_pic3,
                R.drawable.food_pic4,
                R.drawable.food_pic5
            )

            val pagerState = rememberPagerState { images.size }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) { idx ->
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    painter = painterResource(id = images[idx]),
                    contentDescription = "imagePager",
                    contentScale = ContentScale.Crop
                )
            }

            HomeLazyRow(
                title = "믿고 보는 웨이브 에디터 추천작",
                images = images,
                height = 230,
                width = 140,
            )
            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "실시간 인기 콘텐츠",
                images = images,
                height = 230,
                width = 140,
            )
            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "오직 웨이브에서",
                images = images,
                height = 230,
                width = 140,
            )
            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "오늘의 TOP 20",
                images = images,
                height = 260,
                width = 180,
            )
            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "당한 대로 갚아줄게",
                images = images,
                height = 230,
                width = 140,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()

    ANDANDROIDTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Column{
                    CustomTopAppBar(navController = navController)
                    CustomTopAppBarSecond(navController = navController)

                }

            },
            bottomBar = {
                CustomBottomAppBar(navController = navController)
            }
        ) {
                innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1B1B1B))
                    .padding(innerPadding)
            ){
                NavHost(
                    navController = navController,
                    startDestination = "home",
                ){
                    composable("home") {HomeScreen(
                        navController = navController
                    )}
                    composable("search") {SearchScreen(
                        navController = navController
                    )}
                    composable("profile") {MypageScreen(
                        navController = navController,
                    )}
                }
            }

        }
    }
}