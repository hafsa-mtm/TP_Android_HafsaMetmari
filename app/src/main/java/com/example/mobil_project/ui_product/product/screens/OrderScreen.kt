package com.example.mobil_project.ui_product.product.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobil_project.R
import com.example.mobil_project.auth.AuthManager
import com.example.mobil_project.order.OrderManager
import com.example.mobil_project.cart.CartManager
import com.example.mobil_project.ui_product.product.component.BottomBar

@Composable
fun OrderScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val userId = AuthManager.getCurrentUserId()
    val orders = remember { mutableStateOf(OrderManager.getOrdersForUser(userId)) }
    val cartCount = CartManager.getItems(userId).sumOf { it.quantity }

    Scaffold(
        bottomBar = {
            BottomBar(
                currentScreen = "orders",
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
                .padding(16.dp)
        ) {
            Text("My Orders", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))

            if (orders.value.isEmpty()) {
                Text("You have no orders yet.", style = MaterialTheme.typography.bodyMedium)
            } else {
                orders.value.forEach { order ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Order ID: ${order.id}", style = MaterialTheme.typography.labelSmall)
                            Text("Status: ${order.status}", style = MaterialTheme.typography.labelMedium)
                            Text("Total: ${order.totalAmount} $", style = MaterialTheme.typography.bodyMedium)

                            Spacer(Modifier.height(12.dp))
                            Text("Items:", style = MaterialTheme.typography.labelMedium)

                            order.items.forEach { item ->
                                val imageRes = when (item.imageName) {
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

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    imageRes?.let {
                                        Image(
                                            painter = painterResource(id = it),
                                            contentDescription = item.title,
                                            modifier = Modifier
                                                .size(48.dp)
                                                .padding(end = 12.dp)
                                        )
                                    }

                                    Column {
                                        Text(item.title, style = MaterialTheme.typography.bodyMedium)
                                        Text(
                                            "Qty: ${item.quantity} | Price: ${item.price * item.quantity} $",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onNavigateBack,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Back to Home")
            }
        }
    }
}
