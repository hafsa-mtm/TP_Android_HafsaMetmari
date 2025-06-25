package com.example.mobil_project.data.entities

data class CartItem(
    val id: String,
    val title: String,
    val price: Double,
    var quantity: Int = 1,
    val imageName: String? = null
)
