package com.example.dojochallenge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dojochallenge.presentation.gallery.GalleryScreen
import com.example.dojochallenge.presentation.map.MapScreen
import com.example.dojochallenge.presentation.movies.MoviesScreen
import com.example.dojochallenge.presentation.profile.ProfileScreen

@Composable
fun NavigationWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavigationItem.Profile.route) {
        composable(BottomNavigationItem.Profile.route) {
            ProfileScreen()
        }
        composable(BottomNavigationItem.Movies.route) {
            MoviesScreen()
        }
        composable(BottomNavigationItem.Map.route) {
            MapScreen()
        }
        composable(BottomNavigationItem.Gallery.route) {
            GalleryScreen()
        }
    }
}
