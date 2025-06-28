package com.example.mobil_project.ui_product.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobil_project.data.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
import com.example.mobil_project.data.entities.Product

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductViewState())
    val state: StateFlow<ProductViewState> = _state
    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory

    // This is your single source of truth for all products with updated quantities
    private var allProducts: List<Product> = emptyList()

    fun handleIntent(intent: ProductIntent) {
        when (intent) {
            is ProductIntent.LoadProducts -> {
                viewModelScope.launch {
                    loadProducts()
                }
            }
            is ProductIntent.FilterByCategory -> {
                filterProducts(intent.category)
            }
            is ProductIntent.AddToCart -> {
                addToCart(intent.productId, intent.quantity)
            }
        }
    }

    private suspend fun loadProducts() {
        // Only load if products not already loaded to avoid overwriting quantity updates
        if (allProducts.isNotEmpty()) {
            Log.d("ProductViewModel", "Products already loaded, skipping reload")
            // Just emit current filtered products based on selected category
            filterProducts(_selectedCategory.value)
            return
        }

        _state.value = _state.value.copy(isLoading = true, error = null)
        try {
            Log.d("ProductViewModel", "Loading products from repository...")
            allProducts = repository.getProducts()
            // Initially show all products
            _state.value = ProductViewState(
                isLoading = false,
                products = allProducts,
                allProducts = allProducts
            )
        } catch (e: Exception) {
            Log.e("ProductViewModel", "Error loading products", e)
            _state.value = ProductViewState(
                isLoading = false,
                error = e.message ?: "Error fetching products"
            )
        }
    }

    private fun addToCart(productId: String, quantity: Int) {
        Log.d("ProductViewModel", "Add to cart productId=$productId qty=$quantity")

        // Update the quantity in allProducts
        val updatedProducts = allProducts.map { product ->
            if (product.productId == productId && product.quantity != null) {
                val newQuantity = (product.quantity - quantity).coerceAtLeast(0)
                product.copy(quantity = newQuantity)
            } else {
                product
            }
        }

        allProducts = updatedProducts

        // Update state with filtered list based on current selected category
        filterProducts(_selectedCategory.value)
    }

    private fun filterProducts(category: String) {
        _selectedCategory.value = category

        val filteredProducts = when (category) {
            "All" -> allProducts
            else -> allProducts.filter { it.category?.equals(category, ignoreCase = true) == true }
        }

        _state.value = _state.value.copy(
            products = filteredProducts,
            allProducts = allProducts // Maintain current updated full list
        )
    }
}
