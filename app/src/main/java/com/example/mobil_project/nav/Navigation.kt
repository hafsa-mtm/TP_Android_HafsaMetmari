// nav/AppNavigation.kt
package com.example.mobil_project.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobil_project.auth.AuthManager
import com.example.mobil_project.ui_product.product.ProductViewModel
import com.example.mobil_project.ui_product.product.component.DetailsScreen
import com.example.mobil_project.ui_product.product.screens.CartScreen
import com.example.mobil_project.ui_product.product.screens.ConfirmOrderScreen
import com.example.mobil_project.ui_product.product.screens.HomeScreen
import com.example.mobil_project.ui_product.product.screens.LoginScreen
import com.example.mobil_project.ui_product.product.screens.OrderScreen
import com.example.mobil_project.ui_product.product.screens.SignUpScreen
import com.example.mobil_project.ui_product.product.screens.admin.AdminDashboard

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
                onLoginSuccess = { isAdmin ->
                    if (isAdmin) {
                        navController.navigate("admin_dashboard") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
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
                onNavigateToDetails = { productId -> navController.navigate("productDetails/${productId}") },
                onNavigateToCart = { navController.navigate("cart") },
                onNavigateToOrders = { navController.navigate("orders") },
                onLogout = {
                    AuthManager.logout()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }


        composable("productDetails/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            DetailsScreen(productId = productId, viewModel = productViewModel)
        }

        composable("cart") {
            CartScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToConfirm = {
                    navController.navigate("confirm_order") // ✅ Navigation works
                }
            )
        }

        // In AppNavigation.kt
        composable("orders") {
            OrderScreen(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable("confirm_order") {
            ConfirmOrderScreen(
                onOrderPlaced = {
                    navController.navigate("orders") {
                        popUpTo("home") { inclusive = false }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }


        composable("admin_dashboard") {
            AdminDashboard(onLogout = {
                AuthManager.logout()
                navController.navigate("login") {
                    popUpTo("admin_dashboard") { inclusive = true }
                }
            })
        }
    }
}
