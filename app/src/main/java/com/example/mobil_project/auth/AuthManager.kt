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
    // AuthManager.kt additions

    fun getAllUsers(): List<User> = users.toList()

    fun addUser(user: User) {
        users.add(user)
    }

    fun updateUserAt(index: Int, updatedUser: User) {
        if (index in users.indices) {
            users[index] = updatedUser
            // If the updated user is the currentUser, update currentUser reference too
            if (currentUser?.email == updatedUser.email) {
                currentUser = updatedUser
            }
        }
    }

    fun deleteUserAt(index: Int) {
        if (index in users.indices) {
            // If deleting current user, logout
            if (currentUser?.email == users[index].email) {
                logout()
            }
            users.removeAt(index)
        }
    }

    var currentUser: User? = null
        private set

    fun login(email: String, password: String): Boolean {
        currentUser = users.find { user ->
            user.email.equals(email, ignoreCase = true) && user.password == password
        }
        return currentUser != null
    }

    fun signUp(firstName: String, lastName: String, age: Int, email: String, password: String): Boolean {
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

    fun updateUser(firstName: String, lastName: String, age: Int, password: String) {
        currentUser?.let { user ->
            val index = users.indexOfFirst { it.email.equals(user.email, ignoreCase = true) }
            if (index != -1) {
                val updatedUser = User(
                    firstName = firstName,
                    lastName = lastName,
                    age = age,
                    email = user.email,
                    password = password
                )
                users[index] = updatedUser
                currentUser = updatedUser
            }
        }
    }

    fun isAdmin(): Boolean {
        return currentUser?.email?.endsWith("@admin.com") == true
    }

    fun logout() {
        currentUser = null
    }

    fun getCurrentUserId(): String {
        return currentUser?.email ?: "guest"
    }
}
