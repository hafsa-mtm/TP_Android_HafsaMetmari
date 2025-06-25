package com.example.mobil_project.ui_product.product


import com.example.mobil_project.data.entities.Product

data class ProductViewState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null,
    val allProducts: List<Product> = emptyList(),

)
