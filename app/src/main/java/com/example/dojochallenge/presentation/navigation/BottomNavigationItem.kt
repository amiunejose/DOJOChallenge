package com.example.dojochallenge.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedItem: ImageVector,
    val unselectedItem: ImageVector
) {
    data object Profile : BottomNavigationItem(
        title = "Profile",
        route = Profile::class.java.simpleName,
        selectedItem = Icons.Filled.AccountCircle,
        unselectedItem = Icons.Outlined.AccountCircle
    )

    data object Movies : BottomNavigationItem(
        title = "Movies",
        route = Movies::class.java.simpleName,
        selectedItem = Icons.Filled.PlayArrow,
        unselectedItem = Icons.Outlined.PlayArrow
    )

    data object Map : BottomNavigationItem(
        title = "Map",
        route = Map::class.java.simpleName,
        selectedItem = Icons.Filled.LocationOn,
        unselectedItem = Icons.Outlined.LocationOn
    )

    data object Gallery : BottomNavigationItem(
        title = "Gallery",
        route = Gallery::class.java.simpleName,
        selectedItem = Icons.Filled.Add,
        unselectedItem = Icons.Outlined.Add
    )
}