package org.sopt.and.presentation.mypageScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.sopt.and.ui.components.BottomBar.CustomBottomAppBar
import org.sopt.and.ui.components.MypageScreen.MyPageProfileSection
import org.sopt.and.ui.components.MypageScreen.MyPageProfileSection2
import org.sopt.and.ui.components.MypageScreen.MyPageSubSection

@Composable
fun MypageRoute(
    navController: NavController,
    mypageViewModel: MypageViewModel = hiltViewModel()
){
    val uiState by mypageViewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        mypageViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                MypageContract.MyPageSideEffect.ShowErrorToast -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("사용자 데이터를 불러오는 중 오류가 발생했습니다.")
                    }
                }
            }
        }
    }

    MypageScreen(
        navController = navController,
        snackbarHostState = snackbarHostState,
        myPageUiState = uiState,
    )
}

@Composable
fun MypageScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    myPageUiState: MypageContract.MyPageUiState,
) {


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { CustomBottomAppBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1B1B1B))
                .padding(innerPadding)
        ) {
            MyPageProfileSection(
                deliveredUserName = myPageUiState.userName,
                deliveredUserHobby = myPageUiState.userHobby
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
                contentNumber = myPageUiState.contentCount
            )
            Spacer(modifier = Modifier.height(16.dp))
            MyPageSubSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                title = "관심 프로그램",
                topic = "관심 프로그램",
                contentNumber = 0 // 임시값
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MyPagePreview() {
//    val navController = rememberNavController()
//
//    MypageScreen(navController = navController)
//}


