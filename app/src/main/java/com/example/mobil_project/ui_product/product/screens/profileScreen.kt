package com.example.mobil_project.ui_product.product.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobil_project.auth.AuthManager
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun ProfileScreen(onNavigateBack: () -> Unit) {
    val currentUser = AuthManager.currentUser

    var firstName by remember { mutableStateOf(currentUser?.firstName ?: "") }
    var lastName by remember { mutableStateOf(currentUser?.lastName ?: "") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    if (currentUser == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Error: No user logged in.")
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Text("My Profile", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        // Section 1: Personal Info
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Personal Information", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(12.dp))

                Text("Email: ${currentUser.email}", style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Section 2: Password Change
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Change Password", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("New Password") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Save button & messages
        Button(
            onClick = {
                error = null
                successMessage = null

                if (firstName.isBlank() || lastName.isBlank()) {
                    error = "Please fill in all personal information fields."
                    return@Button
                }

                if (password.isNotEmpty()) {
                    if (password.length < 6) {
                        error = "Password must be at least 6 characters."
                        return@Button
                    }
                    if (password != confirmPassword) {
                        error = "Passwords do not match."
                        return@Button
                    }
                }

                // Call updateUser with new data
                AuthManager.updateUser(
                    firstName = firstName,
                    lastName = lastName,
                    age = currentUser.age,
                    password = if (password.isNotEmpty()) password else currentUser.password
                )
                successMessage = "Profile updated successfully."
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Changes")
        }

        Spacer(modifier = Modifier.height(12.dp))

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 4.dp))
        }

        successMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 4.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back")
        }
    }
}
