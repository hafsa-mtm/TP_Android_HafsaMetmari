package com.example.mobil_project.ui_product.product.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobil_project.auth.AuthManager
import com.example.mobil_project.cart.CartManager
import com.example.mobil_project.order.OrderManager
import com.example.mobil_project.ui_product.product.component.BottomBar

@Composable
fun ConfirmOrderScreen(
    onOrderPlaced: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val currentUser = AuthManager.currentUser
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var selectedPaymentMethod by remember { mutableStateOf("Credit Card") }
    val paymentOptions = listOf("Credit Card", "Cash on Delivery", "PayPal")

    val cartItems = currentUser?.email?.let { CartManager.getItems(it) } ?: emptyList()
    val totalAmount = currentUser?.email?.let { CartManager.totalAmount(it) } ?: 0.0
    val cartCount = cartItems.sumOf { it.quantity }

    Scaffold(
        bottomBar = {
            BottomBar(
                currentScreen = "", // not specifically highlighted
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
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Review & Confirm Order", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))

            if (currentUser == null) {
                Text("Error: No user logged in.")
            } else {
                Text("Name: ${currentUser.firstName} ${currentUser.lastName}")
                Text("Email: ${currentUser.email}")

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Delivery Address") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                Text("Select Payment Method:", style = MaterialTheme.typography.titleMedium)
                paymentOptions.forEach { method ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedPaymentMethod == method,
                            onClick = { selectedPaymentMethod = method }
                        )
                        Text(method, modifier = Modifier.padding(start = 8.dp))
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text("Order Summary:", style = MaterialTheme.typography.titleMedium)
                cartItems.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${item.title} x${item.quantity}")
                        Text("${item.price * item.quantity} $")
                    }
                }

                Divider(Modifier.padding(vertical = 8.dp))
                Text("Total: $totalAmount $", style = MaterialTheme.typography.titleMedium)

                Spacer(Modifier.height(16.dp))

                Button(onClick = {
                    if (address.isBlank() || phone.isBlank()) {
                        error = "Please fill in all fields."
                        return@Button
                    }
                    if (cartItems.isEmpty()) {
                        error = "Your cart is empty."
                        return@Button
                    }

                    OrderManager.createOrder(
                        userId = currentUser.email,
                        items = cartItems,
                        address = address,
                        phoneNumber = phone
                    )
                    CartManager.clearCart(currentUser.email)
                    onOrderPlaced()
                }) {
                    Text("Confirm and Place Order ($selectedPaymentMethod)")
                }

                error?.let {
                    Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
                }

                Spacer(Modifier.height(16.dp))
                Button(onClick = onNavigateBack) {
                    Text("Back to Cart")
                }
            }
        }
    }
}
