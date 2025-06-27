package com.example.mobil_project.order

import com.example.mobil_project.data.entities.CartItem
import com.example.mobil_project.data.entities.Order

object OrderManager {
    private val orders = mutableListOf<Order>()

    fun createOrder(userId: String, items: List<CartItem>, address: String, phoneNumber: String ,paymentMethod: String): Order {
        val order = Order(
            id = System.currentTimeMillis().toString(),
            userId = userId,
            items = items,
            status = "Pending",
            totalAmount = items.sumOf { it.price * it.quantity },
            date = System.currentTimeMillis(),
            address = address,
            phoneNumber = phoneNumber,
            paymentMethod = paymentMethod
        )
        orders.add(order)
        return order
    }

    fun getAllOrders(): List<Order> = orders.toList()
    fun getOrdersForUser(userId: String): List<Order> {
        return orders.filter { it.userId == userId }
    }

    fun updateOrderStatus(orderId: String, status: String) {
        orders.find { it.id == orderId }?.let {
            val index = orders.indexOf(it)
            orders[index] = it.copy(status = status)
        }
    }
}
