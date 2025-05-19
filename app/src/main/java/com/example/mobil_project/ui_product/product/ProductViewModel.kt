package com.example.mobil_project.ui_product.product

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.mobil_project.data.repo.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _state = MutableStateFlow(ProductViewState())
    val state: StateFlow<ProductViewState> = _state
    private val repository = ProductRepository()

    fun handleIntent(intent: ProductIntent) {
        when (intent) {
            is ProductIntent.LoadProducts -> {
                viewModelScope.launch {
                    loadProducts()
                }
            }
        }
    }

    private suspend fun loadProducts() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        try {
            val products = repository.getProducts()
            _state.value = ProductViewState(isLoading = false, products = products)
        } catch (e: Exception) {
            _state.value =
                ProductViewState(isLoading = false, error = e.message ?: "Error fetching products")
        }
    }
}


