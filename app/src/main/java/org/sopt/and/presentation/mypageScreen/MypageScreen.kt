package org.sopt.and.presentation.mypageScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.components.BottomBar.CustomBottomAppBar
import org.sopt.and.ui.components.MypageScreen.MyPageProfileSection
import org.sopt.and.ui.components.MypageScreen.MyPageProfileSection2
import org.sopt.and.ui.components.MypageScreen.MyPageSubSection
import org.sopt.and.ui.theme.ANDANDROIDTheme
import androidx.compose.runtime.livedata.observeAsState
import org.sopt.and.presentation.main.UserViewModel


@Composable
fun MypageScreen(
    navController: NavController,
    userViewModel: UserViewModel = viewModel(),
    mypageViewModel: MypageViewModel = viewModel()
) {

    var userNameText = userViewModel.userName.collectAsState().value
    //val userName = userViewModel.userName

    Scaffold(
        bottomBar = {
            CustomBottomAppBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1B1B1B))
                .padding(innerPadding)
        ) {
            MyPageProfileSection(
                deliveredUserName = userNameText
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
}

@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    val navController = rememberNavController()

    ANDANDROIDTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                CustomBottomAppBar(navController = navController)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1B1B1B))
                    .padding(innerPadding)
            ) {
                MypageScreen(navController = navController)
            }
        }
    }
}
