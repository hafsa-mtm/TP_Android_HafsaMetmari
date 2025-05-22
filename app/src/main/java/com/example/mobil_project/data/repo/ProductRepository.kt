package com.example.mobil_project.data.repo

import com.example.mobil_project.R
import com.example.mobil_project.data.entities.Product
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor() {
    suspend fun getProducts(): List<Product> {
        delay(2000) // Simulate network/database delay
        return listOf(
            Product(
                productId = "1",
                title = "Laptop",
                description = "15.6 inch Samsung, Intel i7, 16GB RAM",
                price = 899.99,
                quantity = 10,
                R.drawable.laptop
            ),
            Product(
                productId = "2",
                title = "Headphones",
                description = "Wireless Bluetooth Noise Cancelling",
                price = 149.99,
                quantity = 25,
                R.drawable.headphon // make sure this drawable exists
            ),
            Product(
                productId = "3",
                title = "Mouse",
                description = "Wireless Optical Mouse, Ergonomic",
                price = 29.99,
                quantity = 50,
                R.drawable.mouse
            )
        )
    }
}
