// nav/AppNavigation.kt
package com.example.mobil_project.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobil_project.auth.AuthManager
import com.example.mobil_project.ui_product.product.ProductViewModel
import com.example.mobil_project.ui_product.product.component.DetailsScreen
import com.example.mobil_project.ui_product.product.screens.HomeScreen
import com.example.mobil_project.ui_product.product.screens.LoginScreen
import com.example.mobil_project.ui_product.product.screens.SignUpScreen

// nav/AppNavigation.kt
@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String,
    productViewModel: ProductViewModel
) {
    NavHost(navController, startDestination) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onNavigateToSignUp = { navController.navigate("signup") }
            )
        }

        composable("signup") {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.popBackStack()
                    navController.navigate("login")
                },
                onNavigateToLogin = { navController.navigate("login") }
            )
        }

        composable("home") {
            HomeScreen(
                viewModel = productViewModel,
                onNavigateToDetails = { /*...*/ },
                onLogout = {
                    AuthManager.logout()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}