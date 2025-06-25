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

    // Store original unfiltered products
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
        }
    }

    private suspend fun loadProducts() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        try {
            Log.d("ProductViewModel", "Loading products...")
            allProducts = repository.getProducts()
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

    private fun filterProducts(category: String) {
        _selectedCategory.value = category

        val filteredProducts = when (category) {
            "All" -> allProducts
            else -> allProducts.filter { it.category?.equals(category, ignoreCase = true) == true }
        }

        _state.value = _state.value.copy(
            products = filteredProducts,
            allProducts = allProducts // Maintain original list
        )
    }
}