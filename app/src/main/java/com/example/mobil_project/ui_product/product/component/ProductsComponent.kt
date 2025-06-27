package com.example.mobil_project.ui_product.product.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobil_project.data.entities.Product
import com.example.mobil_project.data.entities.ProductItem

@Composable
fun ProductsList(
    products: List<Product>,
    onNavigateToDetails: (String) -> Unit,
    onAddToCart: (Product) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(products) { product ->
            ProductItem(
                product = product,
                onNavigateToDetails = onNavigateToDetails,
                onAddToCartClick = onAddToCart
            )
        }
    }
}