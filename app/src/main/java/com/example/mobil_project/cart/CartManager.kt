package com.example.mobil_project.cart

// auth/CartManager.kt
import com.example.mobil_project.data.entities.CartItem

// cart/CartManager.kt
object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        val existingItem = cartItems.find { it.id == item.id }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            cartItems.add(item)
        }
    }

    fun removeItem(itemId: String) {
        cartItems.removeIf { it.id == itemId }
    }

    fun getItems(): List<CartItem> = cartItems.toList()

    fun clearCart() {
        cartItems.clear()
    }

    fun totalAmount(): Double {
        return cartItems.sumOf { it.price * it.quantity }
    }
}
