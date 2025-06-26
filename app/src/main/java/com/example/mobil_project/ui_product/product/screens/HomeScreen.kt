package com.example.mobil_project.ui_product.product.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobil_project.auth.AuthManager
import com.example.mobil_project.cart.CartManager
import com.example.mobil_project.data.entities.CartItem
import com.example.mobil_project.ui_product.product.ProductIntent
import com.example.mobil_project.ui_product.product.ProductViewModel
import com.example.mobil_project.ui_product.product.component.FilterBar
import com.example.mobil_project.ui_product.product.component.ProductsList

@Composable
fun HomeScreen(
    viewModel: ProductViewModel = viewModel(),
    onNavigateToDetails: (String) -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onLogout: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val userId = AuthManager.getCurrentUserId()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ProductIntent.LoadProducts)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // 🔵 App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Products",
                style = MaterialTheme.typography.headlineSmall
            )
            Row {
                TextButton(onClick = onNavigateToCart) {
                    Text("My Cart")
                }
                TextButton(onClick = onNavigateToOrders) {
                    Text("My Orders")
                }
                TextButton(onClick = onNavigateToProfile) {
                    Text("Profile")
                }
                TextButton(onClick = onLogout) {
                    Text("Logout")
                }
            }
        }

        Divider()

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            FilterBar(viewModel = viewModel)

            Spacer(modifier = Modifier.height(8.dp))

            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
                }
                state.error != null -> {
                    Text(
                        text = "Error: ${state.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                }
                else -> {
                    ProductsList(
                        products = state.products,
                        onNavigateToDetails = onNavigateToDetails,
                        onAddToCart = { product ->
                            if (userId != null) {
                                CartManager.addItem(
                                    userId,
                                    CartItem(
                                        id = product.productId,
                                        title = product.title ?: "No title",
                                        price = product.price ?: 0.0,
                                        imageName = product.imageName
                                    )
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}
