package com.example.mobil_project.data.entities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobil_project.R

@Composable
fun ProductItem(product: Product) {
    Column(modifier = Modifier.padding(8.dp)) {
        // Map image names to drawable resources
        val imageRes = when (product.imageName) {
            "headphon" -> R.drawable.headphon // Actual filename
            "laptop"   -> R.drawable.laptop      // Must match
            "mouse"    -> R.drawable.mouse
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
        Text(text = product.title ?: "")
        Text(text = "$${product.price ?: 0.0}")
    }
}