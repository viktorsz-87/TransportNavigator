package com.andronest.repository

import android.util.Log
import com.andronest.di.TransportNavigatorApp
import com.andronest.model.response.ArrivalsResponse
import com.andronest.model.response.NearbyStopsResponse
import com.andronest.model.response.TripResponse
import com.andronest.retrofit.ResRobotAPI
import com.andronest.room.FavoriteStopEntity
import com.andronest.room.FavoritesDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TravelRepository @Inject constructor(
    val resRobotAPI: ResRobotAPI,
    val dao: FavoritesDao
) {
    /* Room */

    suspend fun saveToFavorites(stop: FavoriteStopEntity){
        dao.insert(stop)
    }

    suspend fun deleteFavorite(stopId: String){
        dao.delete(stopId)
    }

    suspend fun getAllFavorites(): List<FavoriteStopEntity> {
        return dao.getAllStops()
    }

    /* Retrofit - ResRobot */

    suspend fun getUserStopId(): String {
        return try {
            val stopId = resRobotAPI.getNearbyStops(
                TransportNavigatorApp.userLatitude.toString(),
                TransportNavigatorApp.userLongitude.toString()
            ).body()?.stopLocationOrCoordLocation?.firstNotNullOfOrNull { it.stopLocation.extId }

            return stopId ?: ""
        } catch (e: Exception) {
            Log.e("StopId", "Error fetching stop ID", e)
            ""  // Return empty string on error
        }
    }

    suspend fun getNearbyStops(
        lat: String,
        long: String
    ): List<NearbyStopsResponse.StopLocationOrCoordLocation> {

        try {
            val result = resRobotAPI.getNearbyStops(latitude = lat, longitude = long)

            return when {
                result.isSuccessful -> result.body()?.stopLocationOrCoordLocation.orEmpty()
                else -> emptyList()
            }
        } catch (e: Exception) {
            Log.d("TravelRepository", "error: " + e.message)
            return emptyList()
        }
    }

    suspend fun getArrivals(id: String): List<ArrivalsResponse.Arrival> {

        try {
            val result = resRobotAPI.getArrivals(id = id)

            return when {
                result.isSuccessful -> result.body()?.arrival.orEmpty()
                else -> emptyList()
            }

        } catch (e: Exception) {
            Log.d("TravelRepository", "error: " + e.message)
            return emptyList()
        }
    }

    suspend fun getTripRoute(originStopId: String, destStopId: String): List<TripResponse.Trip> {
        try {
            val result = resRobotAPI.getTripRoute(originId = originStopId, destId = destStopId)

            return when {
                result.isSuccessful -> result.body()?.trip.orEmpty()
                else -> emptyList()
            }

        } catch (e: Exception) {
            Log.d("TravelRepository", "error: " + e.message)
            return emptyList()
        }
    }
}

