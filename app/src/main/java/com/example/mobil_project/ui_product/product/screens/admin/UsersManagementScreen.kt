package com.example.mobil_project.ui_product.product.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobil_project.auth.AuthManager
import com.example.mobil_project.data.entities.User

@Composable
fun UsersManagementScreen(
    onNavigateBack: () -> Unit
) {
    var users by remember { mutableStateOf(AuthManager.getAllUsers()) }
    var editingUserIndex by remember { mutableStateOf<Int?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var userToEdit by remember { mutableStateOf<User?>(null) }
    var userToDeleteIndex by remember { mutableStateOf<Int?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Manage Users", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Button(onClick = { showAddDialog = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Add User")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(users) { index, user ->
                UserRow(
                    user = user,
                    onEdit = {
                        userToEdit = user
                        editingUserIndex = index
                        showEditDialog = true
                    },
                    onDelete = {
                        userToDeleteIndex = index
                        showDeleteDialog = true
                    }
                )
                Divider()
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back")
        }
    }

    if (showAddDialog) {
        UserDialog(
            title = "Add User",
            onDismiss = { showAddDialog = false },
            onConfirm = { newUser ->
                AuthManager.addUser(newUser)
                users = AuthManager.getAllUsers()
                showAddDialog = false
            }
        )
    }

    if (showEditDialog && userToEdit != null && editingUserIndex != null) {
        UserDialog(
            title = "Edit User",
            user = userToEdit,
            onDismiss = { showEditDialog = false },
            onConfirm = { updatedUser ->
                AuthManager.updateUserAt(editingUserIndex!!, updatedUser)
                users = AuthManager.getAllUsers()
                showEditDialog = false
            }
        )
    }

    if (showDeleteDialog && userToDeleteIndex != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    AuthManager.deleteUserAt(userToDeleteIndex!!)
                    users = AuthManager.getAllUsers()
                    showDeleteDialog = false
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            },
            title = { Text("Delete User") },
            text = { Text("Are you sure you want to delete this user?") }
        )
    }
}

@Composable
fun UserRow(user: User, onEdit: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("${user.firstName} ${user.lastName}", style = MaterialTheme.typography.bodyLarge)
            Text("Age: ${user.age}, Email: ${user.email}", style = MaterialTheme.typography.bodySmall)
        }
        Row {
            TextButton(onClick = onEdit) { Text("Edit") }
            TextButton(onClick = onDelete) { Text("Delete") }
        }
    }
}

@Composable
fun UserDialog(
    title: String,
    user: User? = null,
    onDismiss: () -> Unit,
    onConfirm: (User) -> Unit
) {
    var firstName by remember { mutableStateOf(user?.firstName ?: "") }
    var lastName by remember { mutableStateOf(user?.lastName ?: "") }
    var ageText by remember { mutableStateOf(user?.age?.toString() ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var password by remember { mutableStateOf(user?.password ?: "") }
    var error by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = ageText,
                    onValueChange = { ageText = it.filter { ch -> ch.isDigit() } },
                    label = { Text("Age") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    enabled = user == null // disable editing email if editing existing user (email as key)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true
                )
                error?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (firstName.isBlank() || lastName.isBlank() || ageText.isBlank() || email.isBlank() || password.isBlank()) {
                    error = "All fields are required"
                    return@TextButton
                }
                val age = ageText.toIntOrNull()
                if (age == null) {
                    error = "Invalid age"
                    return@TextButton
                }
                val newUser = User(firstName, lastName, age, email, password)
                onConfirm(newUser)
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
