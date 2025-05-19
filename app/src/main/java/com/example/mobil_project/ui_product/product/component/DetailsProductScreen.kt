package com.example.mobil_project.ui_product.product.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobil_project.ui_product.product.ProductViewModel

@Composable
fun DetailsScreen(productId: String, viewModel: ProductViewModel) {
    val product = viewModel.state.value.products.find { it.productId == productId }

    if (product != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Title: ${product.title}")
            Text(text = "Description: ${product.description}")
            Text(text = "Price: $${product.price}")
            Text(text = "Quantity: ${product.quantity}")
        }
    } else {
        Text(text = "Product not found!")
    }
}
