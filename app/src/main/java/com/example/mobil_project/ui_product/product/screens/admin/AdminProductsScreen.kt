package com.example.mobil_project.ui_product.product.screens.admin

// ui_product/product/screens/admin/AdminProductsScreen.kt
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobil_project.data.entities.Product
import com.example.mobil_project.ui_product.product.ProductViewModel
import com.example.mobil_project.ui_product.product.component.AdminProductItem

@Composable
fun AdminProductsScreen(
    viewModel: ProductViewModel,
    onNavigateBack: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        // Load products when screen appears
        viewModel.handleIntent(com.example.mobil_project.ui_product.product.ProductIntent.LoadProducts)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "All Products",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (state.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (!state.value.error.isNullOrEmpty()) {
            Text("Error: ${state.value.error}")
        } else {
            LazyColumn {
                items(state.value.products) { product ->
                    AdminProductItem(product = product,
                        onEdit = {
                            // TODO: Implement Edit Logic
                        },
                        onDelete = {
                            // TODO: Implement Delete Logic
                        }
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        }

        Spacer(Modifier.weight(1f))

        Button(onClick = onNavigateBack, modifier = Modifier.align(Alignment.End)) {
            Text("Back to Dashboard")
        }
    }
}
