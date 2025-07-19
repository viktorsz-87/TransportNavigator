package com.andronest.navigation

import android.net.Uri
import com.andronest.model.response.TripResponse
import com.google.gson.Gson

sealed class Navigation(val route: String) {

    object SearchScreen: Navigation("Search")
    object ArrivalsScreen: Navigation("Arrivals?id={id}"){
        fun createRoute(id: String): String {
            return "Arrivals?id=$id"
        }
    }
    object MapScreen: Navigation("Map?trip={trip}"){
        fun createRoute(trip: TripResponse.Trip): String {
           return "Map?trip=${Uri.encode(Gson().toJson(trip))}"
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