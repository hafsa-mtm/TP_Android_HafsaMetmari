// ui_product/product/screens/CartScreen.kt
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
import com.example.mobil_project.cart.CartManager
import com.example.mobil_project.data.entities.CartItem

@Composable
fun CartScreen(onNavigateBack: () -> Unit) {
    val cartItems = remember { mutableStateOf(CartManager.getItems()) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        Text("My Cart", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        if (cartItems.value.isEmpty()) {
            Text("Your cart is empty.")
        } else {
            cartItems.value.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    verticalAlignment = CenterVertically
                ) {
                    // Get the image resource
                    val imageRes = when (item.imageName) {
                        "headphon" -> R.drawable.headphon
                        "laptop" -> R.drawable.laptop
                        "mouse" -> R.drawable.mouse
                        else -> null
                    }

                    // Show the image if available
                    imageRes?.let {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = item.title,
                            modifier = Modifier.size(64.dp).padding(end = 8.dp)
                        )
                    }

                    // Text and remove button
                    Column(modifier = Modifier.weight(1f)) {
                        Text("${item.title} x${item.quantity}")
                        Text("${item.price * item.quantity} $")
                    }

                    Button(onClick = {
                        CartManager.removeItem(item.id)
                        cartItems.value = CartManager.getItems()
                    }) {
                        Text("Remove")
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("Total: ${CartManager.totalAmount()} $")
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = onNavigateBack) {
            Text("Back to Home")
        }
    }
}
