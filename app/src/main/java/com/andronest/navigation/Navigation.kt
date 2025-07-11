package com.andronest.navigation

sealed class Navigation(val route: String) {

    object SearchScreen: Navigation("Search")
    object ArrivalsScreen: Navigation("Arrivals")
    object MapRoutesScreen: Navigation("Routes")
    object FavoritesScreen: Navigation("Favorites")


}