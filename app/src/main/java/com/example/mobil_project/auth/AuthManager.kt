// auth/AuthManager.kt
package com.example.mobil_project.auth

import com.example.mobil_project.data.entities.User

object AuthManager {
    private val users = mutableListOf<User>(
        User(
            firstName = "John",
            lastName = "Doe",
            age = 25,
            email = "test@example.com",
            password = "123456"
        )
    )

    var currentUser: User? = null
        private set

    fun login(email: String, password: String): Boolean {
        currentUser = users.find { user ->
            user.email.equals(email, ignoreCase = true) && user.password == password
        }
        return currentUser != null
    }

    fun signUp(
        firstName: String,
        lastName: String,
        age: Int,
        email: String,
        password: String
    ): Boolean {
        if (users.any { it.email.equals(email, ignoreCase = true) }) {
            return false
        }
        users.add(
            User(
                firstName = firstName,
                lastName = lastName,
                age = age,
                email = email,
                password = password
            )
        )
        return true
    }

    fun isAdmin(): Boolean {
        return currentUser?.email?.endsWith("@admin.com") == true
    }

    fun logout() {
        currentUser = null
    }

    // New helper to get current user id (email)
    fun getCurrentUserId(): String {
        return currentUser?.email ?: "guest"
    }
}
