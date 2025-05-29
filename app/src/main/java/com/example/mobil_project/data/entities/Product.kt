package com.example.mobil_project.data.entities
import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("productID")
    val productId: String,
    @SerializedName("productTitle")
    val title: String? = null,
    @SerializedName("productDescription")
    val description: String? = null,
    @SerializedName("productPrice")
    val price: Double? = null,       // Price as a number (nullable)
    @SerializedName("productQuantity")
    val quantity: Int? = null, // Quantity as integer (nullable)
    @SerializedName("productImageResId")
    @DrawableRes val imageResId: Int? = null
)
