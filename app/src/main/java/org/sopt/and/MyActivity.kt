package org.sopt.and

import android.content.Intent
import android.graphics.Paint
import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import org.sopt.and.ui.components.BottomBar.CustomBottomAppBar
import org.sopt.and.ui.components.BottomBar.NavIcon
import org.sopt.and.ui.components.MypageScreen.MyPageProfileSection
import org.sopt.and.ui.components.MypageScreen.MyPageProfileSection2
import org.sopt.and.ui.components.MypageScreen.MyPageSubSection
import org.sopt.and.ui.components.TopBar.CustomTopAppBar
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        CustomBottomAppBar(navController = navController)
                    }
                ) {
                    innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "profile",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { HomeScreen() }
                        composable("search") { SearchScreen() }
                        composable("profile") { MypageScreen() }
                    }
                }
            }
        }
    }
}

@Composable
fun MypageScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val activity = context as? ComponentActivity

    val deliveredEmail = activity?.intent?.getStringExtra("email") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1B1B))
    ) {
        MyPageProfileSection(
            deliveredEmail = deliveredEmail
        )
        Spacer(modifier = Modifier.height(0.5.dp))
        MyPageProfileSection2(
            sectionDescription = "첫 결제 시 첫 달 100원!",
            connectedUrl = ""
        )
        MyPageProfileSection2(
            sectionDescription = "현재 보유하신 이용권이 없습니다.",
            connectedUrl = ""
        )
        Spacer(modifier = Modifier.height(0.5.dp))


        MyPageSubSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            title = "전체 시청내역",
            topic = "시청내역",
            contentNumber = 0,  /* 임시로 0개 고정함 */
        )

        Spacer(
            modifier = Modifier.weight(0.2f)
        )

        MyPageSubSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            title = "관심 프로그램",
            topic = "관심 프로그램",
            contentNumber = 0,  /* 임시로 0개 고정함 */
        )


        Spacer(modifier = Modifier.weight(1f))
    }

}

@Preview(showBackground = true)
@Composable
fun MypagePreview3() {
    val navController = rememberNavController()

    ANDANDROIDTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                CustomBottomAppBar(navController = navController)
            }
        ) {
            innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ){
                composable("home") {HomeScreen()}
                composable("search") {SearchScreen()}
                composable("profile") {MypageScreen()}
            }
        }
    }
}