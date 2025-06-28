// data/entities/Product.kt
package com.example.mobil_project.data.entities

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("productId")
    val productId: String,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("quantity")
    val quantity: Int? = null,
    @SerializedName("imageRes")
    val imageName: String? = null,
    @SerializedName("category")
    val category: String? = null
)