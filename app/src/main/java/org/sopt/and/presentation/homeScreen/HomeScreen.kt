package org.sopt.and.presentation.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.presentation.mypageScreen.MypageScreen
import org.sopt.and.presentation.searchScreen.SearchScreen
import org.sopt.and.ui.components.BottomBar.CustomBottomAppBar
import org.sopt.and.ui.components.HomeScreen.HomeLazyRow
import org.sopt.and.ui.components.TopBar.CustomTopAppBar
import org.sopt.and.ui.components.TopBar.CustomTopAppBarSecond
import org.sopt.and.ui.theme.ANDANDROIDTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Column(
                modifier = modifier.fillMaxWidth()
            ){
                CustomTopAppBar(navController = navController)
                CustomTopAppBarSecond(navController = navController)
            }
        },
        bottomBar = {
            CustomBottomAppBar(navController = navController)
        }
    ) { it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color(0xFF1B1B1B))
                .padding(all = 10.dp)
        ) {

            val pagerState = rememberPagerState { homeViewModel.mainPagerImages.size }

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
                    painter = painterResource(id = homeViewModel.mainPagerImages[idx]),
                    contentDescription = "imagePager",
                    contentScale = ContentScale.Crop
                )
            }

            HomeLazyRow(
                title = "믿고 보는 웨이브 에디터 추천작",
                images = homeViewModel.mainPagerImages,
                height = 230,
                width = 140,
            )
            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "실시간 인기 콘텐츠",
                images = homeViewModel.mainPagerImages,
                height = 230,
                width = 140,
            )
            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "오직 웨이브에서",
                images = homeViewModel.mainPagerImages,
                height = 230,
                width = 140,
            )
            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "오늘의 TOP 20",
                images = homeViewModel.mainPagerImages,
                height = 260,
                width = 180,
            )
            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "당한 대로 갚아줄게",
                images = homeViewModel.mainPagerImages,
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
    val homeViewModel = HomeViewModel()

    HomeScreen(
        navController = navController,
        homeViewModel = homeViewModel
    )

}