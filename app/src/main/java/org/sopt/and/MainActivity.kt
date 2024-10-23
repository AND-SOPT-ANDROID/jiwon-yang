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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.theme.ANDANDROIDTheme
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

//로그인 성공 시 로그인한 이메일을 viewmodel에 담아 전역변수로 관리..
class UserViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    fun setEmail(newEmail: String) {
        _email.value = newEmail
    }
}

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
                                navigateToLoginScreen = {
                                    emailText, passwordText -> navController.navigate(LoginScreen(emailText, passwordText))
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
                                userViewModel = userViewModel
                            )
                        }

                    }
                }
            }
        }
    }
}
