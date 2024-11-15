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
import org.sopt.and.presentation.loginScreen.LoginScreen
import org.sopt.and.presentation.mypageScreen.MypageScreen
import org.sopt.and.presentation.searchScreen.SearchScreen
import org.sopt.and.presentation.signupScreen.SignUpScreen
import org.sopt.and.presentation.homeScreen.HomeScreen
import org.sopt.and.presentation.homeScreen.HomeViewModel
import org.sopt.and.presentation.main.UserViewModel
import org.sopt.and.presentation.mypageScreen.MypageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val userViewModel: UserViewModel = viewModel()
                    val mypageViewModel : MypageViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = SignUpScreen,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable<SignUpScreen> {
                            SignUpScreen(
                                navigateToLoginScreen = { userNameText, passwordText ->
                                    navController.navigate(LoginScreen(userNameText, passwordText))
                                }
                            )
                        }

                        composable<LoginScreen> { backStackEntry ->
                            val item = backStackEntry.toRoute<LoginScreen>()
                            val scope = rememberCoroutineScope()
                            val snackbarHostState = remember { SnackbarHostState() }
                            LoginScreen(
                                userNameText = item.userNameText,
                                passwordText = item.passwordText,
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                navigateToHomeScreen = {
                                    navController.navigate("home")
                                },
                                userViewModel = userViewModel
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
                                userViewModel = userViewModel,
                                mypageViewModel = mypageViewModel
                            )
                        }

                    }
                }
            }
        }
    }
}
