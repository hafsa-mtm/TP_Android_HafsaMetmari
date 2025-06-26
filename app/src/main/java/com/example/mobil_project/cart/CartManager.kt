package com.example.mobil_project.cart

// auth/CartManager.kt
import com.example.mobil_project.data.entities.CartItem

object CartManager {
    private val userCarts = mutableMapOf<String, MutableList<CartItem>>()

    fun addItem(userId: String, item: CartItem) {
        val cartItems = userCarts.getOrPut(userId) { mutableListOf() }
        val existingItem = cartItems.find { it.id == item.id }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            cartItems.add(item)
        }
    }
    fun validateCartItems(cartItems: List<CartItem>): List<String> {
        val errors = mutableListOf<String>()
        for (item in cartItems) {
            if (item.quantity <= 0) {
                errors.add("${item.title} has invalid quantity.")
            }
        }
        if (cartItems.isEmpty()) {
            errors.add("Cart is empty.")
        }
        return errors
    }

    fun removeItem(userId: String, itemId: String) {
        userCarts[userId]?.removeIf { it.id == itemId }
    }

    fun getItems(userId: String): List<CartItem> = userCarts[userId]?.toList() ?: emptyList()

    fun clearCart(userId: String) {
        userCarts[userId]?.clear()
    }

    fun totalAmount(userId: String): Double {
        return userCarts[userId]?.sumOf { it.price * it.quantity } ?: 0.0
    }
}
