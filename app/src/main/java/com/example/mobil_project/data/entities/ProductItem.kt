package com.example.mobil_project.data.entities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobil_project.data.entities.Product

@Composable
fun ProductItem(product: Product) {
    Column(modifier = Modifier.padding(8.dp)) {
        product.imageResId?.let { imageRes ->
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 8.dp)
            )
        }
        Text(text = product.title ?: "")
        Text(text = "$${product.price ?: 0.0}")
    }
}