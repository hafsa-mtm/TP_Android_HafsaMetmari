package com.example.mobil_project.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobil_project.ui_product.product.ProductViewModel
import com.example.mobil_project.ui_product.product.screens.HomeScreen
import com.example.mobil_project.ui_product.product.component.DetailsScreen  // <-- Import your DetailsScreen here

@Composable
fun AppNavigation(viewModel: ProductViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToDetails = { productId ->
                    navController.navigate("productDetails/$productId")
                }
            )
        }

        composable(
            route = "productDetails/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            DetailsScreen(productId = productId, viewModel = viewModel)
        }
    }
}
