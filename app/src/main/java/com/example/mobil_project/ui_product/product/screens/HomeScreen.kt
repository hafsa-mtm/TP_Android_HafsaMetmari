package com.example.mobil_project.ui_product.product.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobil_project.ui_product.product.ProductViewModel
import com.example.mobil_project.ui_product.product.ProductIntent
import com.example.mobil_project.ui_product.product.ProductViewState
import com.example.mobil_project.ui_product.product.component.ProductsList
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(
    viewModel: ProductViewModel = viewModel(),
    onNavigateToDetails: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ProductIntent.LoadProducts)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            state.error != null -> Text(text = "Error: ${state.error}", color = Color.Red)
            else -> ProductsList(products = state.products, onNavigateToDetails)
        }
    }
}
