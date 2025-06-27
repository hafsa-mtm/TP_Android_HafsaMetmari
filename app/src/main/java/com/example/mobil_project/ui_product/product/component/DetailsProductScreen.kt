package com.example.mobil_project.ui_product.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobil_project.R
import com.example.mobil_project.cart.CartManager
import com.example.mobil_project.ui_product.product.ProductViewModel

@Composable
fun DetailsScreen(
    productId: String,
    viewModel: ProductViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onNavigateToProfile: () -> Unit,
    userId: String
) {
    val product = viewModel.state.value.products.find { it.productId == productId }
    val cartCount = CartManager.getItems(userId).size

    Scaffold(
        bottomBar = {
            BottomBar(
                currentScreen = "", // no selection in detail
                onHomeClick = onNavigateToHome,
                onCartClick = onNavigateToCart,
                onOrdersClick = onNavigateToOrders,
                onProfileClick = onNavigateToProfile,
                cartItemCount = cartCount
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val imageRes = when (product?.imageName) {
                "headphon" -> R.drawable.headphon
                "laptop" -> R.drawable.laptop
                "mouse" -> R.drawable.mouse
                "hp_elite" -> R.drawable.hp_elite
                "hp_elitebook" -> R.drawable.hp_elitebook
                "hp_pavilion" -> R.drawable.hp_pavilion
                "lenovo" -> R.drawable.lenovo
                "lenovo_thinkpad" -> R.drawable.lenovo_thinkpad
                "casque" -> R.drawable.casque
                "impriment" -> R.drawable.impriment
                "ecouteur" -> R.drawable.ecouteur
                else -> null
            }

            if (product != null) {
                imageRes?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = product.title,
                        modifier = Modifier.size(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Text(text = "Title: ${product.title}")
                Text(text = "Description: ${product.description}")
                Text(text = "Price: $${product.price}")
                Text(text = "Quantity: ${product.quantity}")
            } else {
                Text(text = "Product not found!")
            }
        }
    }
}
