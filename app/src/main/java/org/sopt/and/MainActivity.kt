package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.theme.ANDANDROIDTheme
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.presentation.homeScreen.HomeRoute
import org.sopt.and.presentation.loginScreen.LoginScreen
import org.sopt.and.presentation.mypageScreen.MypageScreen
import org.sopt.and.presentation.searchScreen.SearchScreen
import org.sopt.and.presentation.signupScreen.SignUpScreen
import org.sopt.and.presentation.homeScreen.HomeScreen
import org.sopt.and.presentation.homeScreen.HomeViewModel
import org.sopt.and.presentation.loginScreen.LoginRoute
import org.sopt.and.presentation.mypageScreen.MypageRoute
import org.sopt.and.presentation.searchScreen.SearchRoute
import org.sopt.and.presentation.signupScreen.SignUpRoute
import org.sopt.and.util.Route

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier
    ){
        composable("signup") {
            SignUpRoute(
                navigateToLoginScreen = {
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }

        composable("login") {
            LoginRoute(
                navigateToHomeScreen = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeRoute(
                navController = navController,
            )
        }

        composable("search") {
            SearchRoute(
                navController = navController,
            )
        }

        composable("mypage") {
            MypageRoute(
                navController = navController,
            )
        }
    }
}

