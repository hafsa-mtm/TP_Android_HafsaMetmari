@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mobil_project.ui_product.product.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobil_project.data.entities.Product
import com.example.mobil_project.ui_product.product.ProductViewModel
import com.example.mobil_project.ui_product.product.component.AdminProductItem

@Composable
fun AdminProductsScreen(
    viewModel: ProductViewModel,
    onNavigateBack: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(com.example.mobil_project.ui_product.product.ProductIntent.LoadProducts)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Product Management", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            if (state.value.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (!state.value.error.isNullOrEmpty()) {
                Text("Error: ${state.value.error}")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.value.products) { product ->
                        AdminProductItem(
                            product = product,
                            onEdit = {
                                // TODO: Implement Edit Logic
                            },
                            onDelete = {
                                // TODO: Implement Delete Logic
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = onNavigateBack, modifier = Modifier.align(Alignment.End)) {
                Text("Back to Dashboard")
            }
        }
    }
}
