@file:OptIn(ExperimentalMaterial3Api::class)

package org.sopt.and

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.sopt.and.ui.components.BottomBar.CustomBottomAppBar
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