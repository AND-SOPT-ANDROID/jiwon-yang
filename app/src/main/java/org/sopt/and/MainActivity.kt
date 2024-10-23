package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.theme.ANDANDROIDTheme
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = SignUpScreen,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable<SignUpScreen> {
                            SignUpScreen(
                                navigateToLoginScreen = {
                                    emailText, passwordText -> navController.navigate(LoginScreen(emailText, passwordText))
                                }
                            )
                        }

                        /*다른 composable route도 추가해주기..*/


                    }
                }
            }
        }
    }


}
