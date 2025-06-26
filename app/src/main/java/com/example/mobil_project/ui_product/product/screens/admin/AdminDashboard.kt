package com.example.mobil_project.ui_product.product.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminDashboard(
    onLogout: () -> Unit,
    onNavigateToUsers: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onNavigateToProducts: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome Admin!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Button(
            onClick = onNavigateToUsers,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("👥 Manage Users")
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onNavigateToOrders,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📋 Manage Orders")
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onNavigateToProducts,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🛍️ Manage Products")
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onNavigateToProfile,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("👤 Profile")
        }
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}
