package com.example.mobil_project.ui_product.product.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobil_project.ui_product.product.ProductViewModel
import com.example.mobil_project.ui_product.product.component.FilterBar
import com.example.mobil_project.ui_product.product.component.ProductsList
import com.example.mobil_project.ui_product.product.ProductIntent

@Composable
fun HomeScreen(
    viewModel: ProductViewModel = viewModel(),
    onNavigateToDetails: (String) -> Unit,
    onLogout: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ProductIntent.LoadProducts)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Custom App Bar (non-experimental)
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
            TextButton(onClick = onLogout) {
                Text("Logout")
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
                state.isLoading -> CircularProgressIndicator(
                    modifier = Modifier.align(CenterHorizontally)
                )
                state.error != null -> Text(
                    text = "Error: ${state.error}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(CenterHorizontally)
                )
                else -> ProductsList(
                    products = state.products,
                    onNavigateToDetails = onNavigateToDetails
                )
            }
        }
    }
}