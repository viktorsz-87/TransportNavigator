package com.andronest.navigation

sealed class Navigation(val route: String) {

    object SearchScreen: Navigation("Search")
    object ArrivalsScreen: Navigation("Arrivals?id={id}"){
        fun createRoute(id: String): String = "$route?=$id"
    }
    object MapRoutesScreen: Navigation("Routes")
    object FavoritesScreen: Navigation("Favorites")


    fun getBaseRoute(): String = route.substringBefore("/")
}