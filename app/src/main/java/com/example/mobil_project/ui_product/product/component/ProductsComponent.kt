package com.example.mobil_project.ui_product.product.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobil_project.data.entities.Product

@Composable
fun ProductsList(products: List<Product>, onNavigateToDetails: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product = product, onNavigateToDetails = onNavigateToDetails)
        }
    }
}
