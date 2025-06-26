package com.example.mobil_project.ui_product.product.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobil_project.R
import com.example.mobil_project.auth.AuthManager
import com.example.mobil_project.cart.CartManager
import com.example.mobil_project.data.entities.CartItem

@Composable
fun CartScreen(
    onNavigateBack: () -> Unit,
    onNavigateToConfirm: () -> Unit // ✅ New callback
) {
    val userId = AuthManager.getCurrentUserId()
    var cartItems by remember { mutableStateOf(CartManager.getItems(userId)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text("My Cart", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        if (cartItems.isEmpty()) {
            Text("Your cart is empty.")
        } else {
            cartItems.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = CenterVertically
                ) {
                    val imageRes = when (item.imageName) {
                        "headphon" -> R.drawable.headphon
                        "laptop" -> R.drawable.laptop
                        "mouse" -> R.drawable.mouse
                        else -> null
                    }

                    imageRes?.let {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = item.title,
                            modifier = Modifier.size(64.dp).padding(end = 8.dp)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text("${item.title} x${item.quantity}")
                        Text("${item.price * item.quantity} $")
                    }

                    Button(onClick = {
                        CartManager.removeItem(userId, item.id)
                        cartItems = CartManager.getItems(userId) // Refresh
                    }) {
                        Text("Remove")
                    }
                }
            }
            Button(onClick = onNavigateToConfirm) {
                Text("Proceed to Checkout")
            }

            Spacer(Modifier.height(16.dp))
            Text("Total: ${CartManager.totalAmount(userId)} $")
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = onNavigateBack) {
            Text("Back to Home")
        }
    }
}
