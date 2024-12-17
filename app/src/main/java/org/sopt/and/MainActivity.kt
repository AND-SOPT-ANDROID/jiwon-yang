package org.sopt.and

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.theme.ANDANDROIDTheme
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import org.sopt.and.presentation.loginScreen.LoginScreen
import org.sopt.and.presentation.mypageScreen.MypageScreen
import org.sopt.and.presentation.searchScreen.SearchScreen
import org.sopt.and.presentation.signupScreen.SignUpScreen
import org.sopt.and.presentation.homeScreen.HomeScreen
import org.sopt.and.presentation.homeScreen.HomeViewModel
import org.sopt.and.presentation.loginScreen.LoginViewModel
import org.sopt.and.presentation.mypageScreen.MypageViewModel
import org.sopt.and.util.Route

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { it
                        val navController = rememberNavController()

                        val context = navController.context

                        NavHost(
                            navController = navController,
                            startDestination = Route.SignUpScreen(userName = "", password = ""),
                            modifier = Modifier
                        ){
                            composable<Route.SignUpScreen> { backStackEntry ->
                                val item = backStackEntry.toRoute<Route.SignUpScreen>()
                                SignUpScreen(
                                    navigateToLoginScreen = {
                                        navController.navigate(Route.LoginScreen){
                                            popUpTo<Route.SignUpScreen> { inclusive = true }
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }

                            composable<Route.LoginScreen> { backStackEntry ->
                                val item = backStackEntry.toRoute<Route.LoginScreen>()
                                LoginScreen(
                                    navigateToHomeScreen = {
                                        navController.navigate(Route.HomeScreen){
                                            popUpTo<Route.HomeScreen> { inclusive = true}
                                            launchSingleTop = true
                                        }
                                    },
                                )
                            }

                            composable<Route.HomeScreen> { backStackEntry ->
                                val item = backStackEntry.toRoute<Route.HomeScreen>()
                                HomeScreen(
                                    homeViewModel = HomeViewModel(),
                                    navController = navController,
                                )
                            }

                            composable<Route.SearchScreen> { backStackEntry ->
                                val item = backStackEntry.toRoute<Route.SearchScreen>()
                                SearchScreen(
                                    navController = navController
                                )
                            }

                            composable<Route.MypageScreen> { backStackEntry ->
                                val item = backStackEntry.toRoute<Route.MypageScreen>()
                                MypageScreen(
                                    navController = navController
                                )
                            }
                        }


                    })
            }
        }
    }
}

