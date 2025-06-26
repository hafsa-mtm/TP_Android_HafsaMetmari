// ui_product/product/component/AdminProductItem.kt
package com.example.mobil_project.ui_product.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobil_project.R
import com.example.mobil_project.data.entities.Product

@Composable
fun AdminProductItem(
    product: Product,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
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
                    modifier = Modifier.size(100.dp).padding(bottom = 8.dp)
                )
            }

            Text("Title: ${product.title}")
            Text("Description: ${product.description}")
            Text("Category: ${product.category ?: "N/A"}")
            Text("Price: ${product.price ?: 0.0} $")
            Text("Qty: ${product.quantity ?: 0}")

            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onEdit) {
                    Text("Edit")
                }
                Button(onClick = onDelete) {
                    Text("Delete")
                }
            }
        }
    }
}
