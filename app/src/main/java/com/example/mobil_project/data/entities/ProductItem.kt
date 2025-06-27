package com.example.mobil_project.data.entities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobil_project.R

@Composable
fun ProductItem(
    product: Product,
    onNavigateToDetails: (String) -> Unit,
    onAddToCartClick: (Product) -> Unit    // new callback
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            // Map image names to actual drawable resources
            val imageRes = when (product.imageName) {
                "headphon" -> R.drawable.headphon
                "laptop" -> R.drawable.laptop
                "mouse" -> R.drawable.mouse
                "hp_elite" -> R.drawable.hp_elite
                "hp_elitebook" -> R.drawable.hp_elitebook
                "hp_pavilion" -> R.drawable.hp_pavilion
                "lenovo" -> R.drawable.lenovo
                "lenovo_thinkpad" -> R.drawable.lenovo_thinkpad
                "casque" -> R.drawable.casque
                "impriment" -> R.drawable.impriment
                "ecouteur" -> R.drawable.ecouteur
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

            Text(text = "Title: ${product.title}")
            Text(text = "Description: ${product.description}")

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { onNavigateToDetails(product.productId) }) {
                    Text(text = "Plus de détails...")
                }
                Button(onClick = { onAddToCartClick(product) }) {
                    Text(text = "Add to Cart")
                }
            }
        }
    }
}
