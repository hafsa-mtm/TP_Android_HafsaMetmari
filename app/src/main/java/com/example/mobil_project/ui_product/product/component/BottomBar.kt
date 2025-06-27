package com.example.mobil_project.ui_product.product.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(
    currentScreen: String,
    onHomeClick: () -> Unit,
    onCartClick: () -> Unit,
    onOrdersClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    cartItemCount: Int = 0,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = currentScreen == "home",
            onClick = onHomeClick,
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    tint = if (currentScreen == "home") MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            },
            label = {
                Text(
                    "Home",
                    color = if (currentScreen == "home") MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            }
        )
        NavigationBarItem(
            selected = currentScreen == "cart",
            onClick = onCartClick,
            icon = {
                BadgedBox(badge = {
                    if (cartItemCount > 0) {
                        Badge {
                            Text(
                                cartItemCount.toString(),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = if (currentScreen == "cart") MaterialTheme.colorScheme.onPrimary
                        else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                    )
                }
            },
            label = {
                Text(
                    "Cart",
                    color = if (currentScreen == "cart") MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            }
        )
        NavigationBarItem(
            selected = currentScreen == "orders",
            onClick = onOrdersClick,
            icon = {
                Icon(
                    Icons.Default.List,
                    contentDescription = "Orders",
                    tint = if (currentScreen == "orders") MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            },
            label = {
                Text(
                    "Orders",
                    color = if (currentScreen == "orders") MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            }
        )
        NavigationBarItem(
            selected = currentScreen == "profile",
            onClick = onProfileClick,
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = if (currentScreen == "profile") MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            },
            label = {
                Text(
                    "Profile",
                    color = if (currentScreen == "profile") MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = onLogoutClick,
            icon = {
                Icon(
                    Icons.Default.ExitToApp,
                    contentDescription = "Logout",
                    tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            },
            label = {
                Text(
                    "Logout",
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            }
        )
    }
}