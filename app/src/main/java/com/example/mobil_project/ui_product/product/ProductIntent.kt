package com.example.mobil_project.ui_product.product

sealed class ProductIntent {
    object LoadProducts : ProductIntent()
    data class FilterByCategory(val category: String) : ProductIntent()
}