package com.example.mobil_project.ui_product.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            // ... (keep original image loading code)

            Text("Title: ${product.title}", color = MaterialTheme.colorScheme.onSurface)
            Text("Description: ${product.description}", color = MaterialTheme.colorScheme.onSurface)
            Text("Category: ${product.category ?: "N/A"}", color = MaterialTheme.colorScheme.onSurface)
            Text("Price: ${product.price ?: 0.0} $", color = MaterialTheme.colorScheme.primary)
            Text("Qty: ${product.quantity ?: 0}", color = MaterialTheme.colorScheme.onSurface)

            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = onEdit,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Edit")
                }
                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text("Delete")
                }
            }
        }
    }
}