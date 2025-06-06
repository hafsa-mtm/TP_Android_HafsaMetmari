package com.example.mobil_project.ui_product.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobil_project.data.entities.Product

@Composable
fun ProductItem(product: Product, onNavigateToDetails: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            // Show image if available
            product.imageResId?.let { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = product.title,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = 8.dp)
                )
            }

            Text(text = "Title: ${product.title}")
            Text(text = "Description: ${product.description}")

            Button(onClick = { onNavigateToDetails(product.productId) }) {
                Text(text = "Plus de détails...")
            }
        }
    }
}
