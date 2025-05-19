package com.example.mobil_project.data.entities
import androidx.annotation.DrawableRes

data class Product(
    val productId: String,
    val title: String? = null,
    val description: String? = null,
    val price: Double? = null,       // Price as a number (nullable)
    val quantity: Int? = null, // Quantity as integer (nullable)
    @DrawableRes val imageResId: Int? = null
)
