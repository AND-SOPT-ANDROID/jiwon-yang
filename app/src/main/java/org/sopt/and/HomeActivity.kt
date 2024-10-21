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
import org.sopt.and.ui.components.BottomBar.CustomBottomAppBar
import org.sopt.and.ui.components.BottomBar.NavIcon
import org.sopt.and.ui.components.TopBar.CustomTopAppBar
import org.sopt.and.ui.theme.ANDANDROIDTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()


            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CustomTopAppBar(navController = navController)
                    },
                    bottomBar = {
                        CustomBottomAppBar(navController = navController)
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF1B1B1B))
                            .padding(innerPadding)
                    ){
                        CustomTopAppBarSecond(navController = navController)

                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable("home") { HomeScreen() }
                            composable("search") { MypageScreen() }
                            composable("profile") { MypageScreen() }
                        }

                    }

                }
            }
        }
    }
}

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFF1B1B1B))
            .padding(all = 10.dp)
    ){

        val images = listOf(
            R.drawable.food_pic1,
            R.drawable.food_pic2,
            R.drawable.food_pic3,
            R.drawable.food_pic4,
            R.drawable.food_pic5
        )

        val pagerState = rememberPagerState {images.size}

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
//            AsyncImage(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp)
//                    .clip(RoundedCornerShape(16.dp)),
//                model = ImageRequest.Builder(LocalContext.current).data(images[idx])
//                    .build(),
//                contentDescription = "imagePager",
//                contentScale = ContentScale.Crop
//            )
        }

        Text("믿고 보는 웨이브 에디터 추천작", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
        LazyRow (
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(start = 8.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(images){ imageRes ->
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    painter = painterResource(id = imageRes),
                    contentDescription = "LazyRow Image",
                    contentScale = ContentScale.Crop
                )
            }

        }

        Text("실시간 인기 콘텐츠", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)

        Text("오직 웨이브에서", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)

        Text("오늘의 TOP 20", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)

        Text("당한 대로 갚아줄게", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)

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
                CustomTopAppBar(navController = navController)
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
                CustomTopAppBarSecond(navController = navController)
                NavHost(
                    navController = navController,
                    startDestination = "home",
                ){
                    composable("home") {HomeScreen()}
                    composable("search") {MypageScreen()}
                    composable("profile") {MypageScreen()}
                }
            }

        }
    }
}