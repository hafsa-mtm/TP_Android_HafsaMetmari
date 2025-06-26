@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mobil_project.ui_product.product.screens.admin

// ui_product/product/screens/admin/AdminOrdersScreen.kt
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobil_project.order.OrderManager
import com.example.mobil_project.data.entities.Order
import androidx.compose.material3.TextFieldDefaults

@Composable
fun AdminOrdersScreen(
    onNavigateBack: () -> Unit
) {
    // Get all orders
    var orders by remember { mutableStateOf(OrderManager.getAllOrders()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "All Orders",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (orders.isEmpty()) {
            Text("No orders found.")
        } else {
            LazyColumn {
                items(orders) { order ->
                    OrderCard(order = order, onStatusChange = { newStatus ->
                        OrderManager.updateOrderStatus(order.id, newStatus)
                        // Refresh orders list after update
                        orders = OrderManager.getAllOrders()
                    })
                    Spacer(Modifier.height(12.dp))
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavigateBack,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Back to Dashboard")
        }
    }
}

@Composable
fun OrderCard(
    order: Order,
    onStatusChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val statuses = listOf("Pending", "Processing", "Shipped", "Delivered", "Cancelled")
    var selectedStatus by remember { mutableStateOf(order.status) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Order ID: ${order.id}", style = MaterialTheme.typography.labelSmall)
            Text("User ID: ${order.userId}", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(4.dp))
            Text("Total: ${order.totalAmount} $", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(8.dp))

            // Items
            Text("Items:", style = MaterialTheme.typography.labelMedium)
            order.items.forEach { item ->
                val imageRes = when (item.imageName) {
                    "headphon" -> com.example.mobil_project.R.drawable.headphon
                    "laptop" -> com.example.mobil_project.R.drawable.laptop
                    "mouse" -> com.example.mobil_project.R.drawable.mouse
                    else -> null
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    imageRes?.let {
                        androidx.compose.foundation.Image(
                            painter = androidx.compose.ui.res.painterResource(id = it),
                            contentDescription = item.title,
                            modifier = Modifier.size(48.dp).padding(end = 12.dp)
                        )
                    }
                    Column {
                        Text(item.title, style = MaterialTheme.typography.bodyMedium)
                        Text("Qty: ${item.quantity} | Price: ${item.price * item.quantity} $",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // Status Dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    readOnly = true,
                    value = selectedStatus,
                    onValueChange = {},
                    label = { Text("Order Status") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    statuses.forEach { status ->
                        DropdownMenuItem(
                            text = { Text(status) },
                            onClick = {
                                selectedStatus = status
                                expanded = false
                                onStatusChange(status)
                            }
                        )
                    }
                }
            }
        }
    }
}
