package com.example.mobil_project.ui_product.product.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobil_project.ui_product.product.ProductIntent
import com.example.mobil_project.ui_product.product.ProductViewModel

@Composable
fun FilterBar(viewModel: ProductViewModel , modifier: Modifier = Modifier) {
    val categories = listOf("All", "Laptops", "Accessories")
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        categories.forEach { category ->
            FilterChip(
                selected = category == selectedCategory,
                onClick = {
                    viewModel.handleIntent(ProductIntent.FilterByCategory(category))
                },
                label = { Text(category) },
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}