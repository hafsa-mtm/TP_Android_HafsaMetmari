package com.example.mobil_project.data.entities

data class User(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val email: String,
    val password: String  // Note: In production, store only hashed passwords
)