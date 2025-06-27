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
import com.example.mobil_project.ui_product.product.screens.ProfileScreen
import com.example.mobil_project.ui_product.product.screens.admin.AdminOrdersScreen
import com.example.mobil_project.ui_product.product.screens.admin.AdminProductsScreen
import com.example.mobil_project.ui_product.product.screens.admin.UsersManagementScreen

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
        composable("profile") {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = { navController.navigate("home") },
                onNavigateToCart = { navController.navigate("cart") },
                onNavigateToOrders = { navController.navigate("orders") },
                onNavigateToProfile = { /* Do nothing or scroll to top */ }
            )
        }
        composable("home") {
            HomeScreen(
                viewModel = productViewModel,
                onNavigateToDetails = { productId -> navController.navigate("productDetails/${productId}") },
                onNavigateToCart = { navController.navigate("cart") },
                onNavigateToOrders = { navController.navigate("orders") },
                onNavigateToProfile = { navController.navigate("profile") },
                onLogout = {
                    AuthManager.logout()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        composable("admin_orders") {
            AdminOrdersScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("productDetails/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            DetailsScreen(
                productId = productId,
                viewModel = productViewModel,
                onNavigateToHome = { navController.navigate("home") },
                onNavigateToCart = { navController.navigate("cart") },
                onNavigateToOrders = { navController.navigate("orders") },
                onNavigateToProfile = { navController.navigate("profile") },
                userId = AuthManager.getCurrentUserId() ?: ""
            )
        }
        composable("admin_products") {
            AdminProductsScreen(
                viewModel = productViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("cart") {
            CartScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToConfirm = {
                    navController.navigate("confirm_order")
                },
                onNavigateToHome = {
                    navController.navigate("home")
                },
                onNavigateToCart = {
                    navController.navigate("cart")
                },
                onNavigateToOrders = {
                    navController.navigate("orders")
                },
                onNavigateToProfile = {
                    navController.navigate("profile")
                }
            )
        }


        // In AppNavigation.kt
        composable("orders") {
            OrderScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onNavigateToCart = {
                    navController.navigate("cart")
                },
                onNavigateToOrders = {
                    // Since you're already on "orders", you can keep this empty or do nothing
                },
                onNavigateToProfile = {
                    navController.navigate("profile")
                }
            )
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
                },
                onNavigateToHome = {
                    navController.navigate("home")
                },
                onNavigateToCart = {
                    navController.navigate("cart")
                },
                onNavigateToOrders = {
                    navController.navigate("orders")
                },
                onNavigateToProfile = {
                    navController.navigate("profile")
                }
            )
        }



        composable("admin_dashboard") {
            AdminDashboard(
                onLogout = {
                    AuthManager.logout()
                    navController.navigate("login") {
                        popUpTo("admin_dashboard") { inclusive = true }
                    }
                },
                onNavigateToUsers = {
                    navController.navigate("admin_users")
                },
                onNavigateToOrders = {
                    navController.navigate("admin_orders")
                },
                onNavigateToProducts = {
                    navController.navigate("admin_products")
                },
                onNavigateToProfile = {      // <-- new navigation lambda
                    navController.navigate("profile")
                }
            )
        }
        composable("admin_users") {
            UsersManagementScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
    }
