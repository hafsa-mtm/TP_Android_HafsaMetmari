package com.example.mobil_project.data.entities

data class Order(
    val id: String,
    val userId: String,
    val items: List<CartItem>,
    val status: String,
    val totalAmount: Double,
    val date: Long,
    val address: String,
    val phoneNumber: String
)

