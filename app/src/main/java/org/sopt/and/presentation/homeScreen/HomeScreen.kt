package org.sopt.and.presentation.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import org.sopt.and.R
import org.sopt.and.ui.components.BottomBar.CustomBottomAppBar
import org.sopt.and.ui.components.HomeScreen.HomeLazyRow
import org.sopt.and.ui.components.TopBar.CustomTopAppBar
import org.sopt.and.ui.components.TopBar.CustomTopAppBarSecond

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    // SideEffect 처리 (네비게이션 및 단발성 이벤트)
    LaunchedEffect(homeViewModel.sideEffect) {
        homeViewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collectLatest { sideEffect ->
            when (sideEffect) {
                is HomeContract.HomeSideEffect.NavigateToDetail -> {
                    navController.navigate("detailScreen/${sideEffect.imageIndex}")
                }
            }
        }
    }

    // 화면이 로드될 때 이벤트 전송
    LaunchedEffect(Unit) {
        homeViewModel.setEvent(HomeContract.HomeEvent.OnScreenLoaded)
    }

    Scaffold(
        topBar = {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                CustomTopAppBar(navController = navController)
                CustomTopAppBarSecond(navController = navController)
            }
        },
        bottomBar = { CustomBottomAppBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color(0xFF1B1B1B))
                .padding(paddingValues)
                .padding(all = 10.dp)
        ) {
            val pagerState = rememberPagerState { uiState.pagerImages.size }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) { index ->
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp)),
                    painter = painterResource(id = uiState.pagerImages[index]),
                    contentDescription = "imagePager",
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "믿고 보는 웨이브 에디터 추천작",
                images = uiState.pagerImages,
                height = 230,
                width = 140,
                onItemClick = { index ->
                    homeViewModel.setEvent(HomeContract.HomeEvent.OnImageClicked(index))
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "실시간 인기 콘텐츠",
                images = uiState.pagerImages,
                height = 230,
                width = 140,
                onItemClick = { index ->
                    homeViewModel.setEvent(HomeContract.HomeEvent.OnImageClicked(index))
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "오직 웨이브에서",
                images = uiState.pagerImages,
                height = 230,
                width = 140,
                onItemClick = { index ->
                    homeViewModel.setEvent(HomeContract.HomeEvent.OnImageClicked(index))
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "오늘의 TOP 20",
                images = uiState.pagerImages,
                height = 260,
                width = 180,
                onItemClick = { index ->
                    homeViewModel.setEvent(HomeContract.HomeEvent.OnImageClicked(index))
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            HomeLazyRow(
                title = "당한 대로 갚아줄게",
                images = uiState.pagerImages,
                height = 230,
                width = 140,
                onItemClick = { index ->
                    homeViewModel.setEvent(HomeContract.HomeEvent.OnImageClicked(index))
                }
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()

    val uiState = HomeContract.HomeUiState(
        pagerImages = listOf(
            R.drawable.food_pic1,
            R.drawable.food_pic2,
            R.drawable.food_pic3,
            R.drawable.food_pic4,
            R.drawable.food_pic5
        )
    )

    val mockViewModel = object : HomeViewModel() {
        init {
            setState { uiState }
        }
    }

    HomeScreen(
        navController = navController,
        homeViewModel = mockViewModel
    )
}