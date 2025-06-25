// MainActivity.kt
package com.example.mobil_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mobil_project.auth.AuthManager
import com.example.mobil_project.nav.AppNavigation
import com.example.mobil_project.ui_product.Theme.Mobil_projectTheme
import com.example.mobil_project.ui_product.product.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mobil_projectTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppRoot(productViewModel)
                }
            }
        }
    }
}

@Composable
fun AppRoot(productViewModel: ProductViewModel) {
    val navController = rememberNavController()
    val startDestination = if (AuthManager.currentUser != null) "home" else "login"

    AppNavigation(
        navController = navController,
        startDestination = startDestination,
        productViewModel = productViewModel
    )
}