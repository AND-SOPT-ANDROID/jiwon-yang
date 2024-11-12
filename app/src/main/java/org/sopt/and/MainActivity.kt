package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.theme.ANDANDROIDTheme
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.sopt.and.presentation.LoginScreen.LoginScreen
import org.sopt.and.presentation.mypageScreen.MypageScreen
import org.sopt.and.presentation.searchScreen.SearchScreen
import org.sopt.and.presentation.signupScreen.SignUpScreen
import org.sopt.and.presentation.homeScreen.HomeScreen
import org.sopt.and.presentation.homeScreen.HomeViewModel
import org.sopt.and.presentation.main.UserViewModel
import org.sopt.and.presentation.mypageScreen.MypageViewModel

//로그인 성공 시 로그인한 이메일을 담아서
// 전역변수로 관리했던 UserViewModel을
// 따로 분리함


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val userViewModel: UserViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = SignUpScreen,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable<SignUpScreen> {
                            SignUpScreen(
                                navigateToLoginScreen = { emailText, passwordText ->
                                    navController.navigate(LoginScreen(emailText, passwordText))
                                }
                            )
                        }

                        composable<LoginScreen> { backStackEntry ->
                            val item = backStackEntry.toRoute<LoginScreen>()
                            val scope = rememberCoroutineScope()
                            val snackbarHostState = remember { SnackbarHostState() }
                            LoginScreen(
                                emailText = item.emailText,
                                passwordText = item.passwordText,
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                navigateToHomeScreen = {
                                    navController.navigate("home")
                                }
                            )
                        }

                        composable("home") {
                            HomeScreen(
                                navController = navController,
                                homeViewModel = HomeViewModel()
                            )
                        }

                        composable("search") {
                            SearchScreen(
                                navController = navController
                            )
                        }

                        composable("profile") {

                            MypageScreen(
                                navController = navController,
                                mypageViewModel = MypageViewModel()
                            )
                        }

                    }
                }
            }
        }
    }
}
