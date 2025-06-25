package com.example.mobil_project.data.entities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobil_project.R

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Image mapping
            val imageRes = when (product.imageName) {
                "headphon" -> R.drawable.headphon
                "laptop" -> R.drawable.laptop
                "mouse" -> R.drawable.mouse
                else -> null
            }

            imageRes?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = product.title,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = 8.dp)
                )
            }

            Text(text = product.title ?: "", style = MaterialTheme.typography.titleMedium)
            Text(text = "Category: ${product.category ?: "N/A"}", style = MaterialTheme.typography.bodySmall)
            Text(text = "$${product.price ?: 0.0}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}