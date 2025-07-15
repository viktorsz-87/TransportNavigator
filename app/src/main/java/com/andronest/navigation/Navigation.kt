package com.andronest.navigation

sealed class Navigation(val route: String) {

    object SearchScreen: Navigation("Search")
    object ArrivalsScreen: Navigation("Arrivals?id={id}"){
        fun createRoute(id: String): String {
            return "Arrivals?id=$id"
        }
    }
    object TripScreen: Navigation("Trip?latitude={latitude}&longitude={longitude}&stopId={stopId}"){

        fun createRoute(latitude: Double, longitude:Double, stopId: String): String {
            return "Trip?latitude=$latitude&longitude=$longitude&stopId=$stopId"
        }
    }
    object FavoritesScreen: Navigation("Favorites")


    fun getBaseRoute(): String = route.substringBefore("?")
}