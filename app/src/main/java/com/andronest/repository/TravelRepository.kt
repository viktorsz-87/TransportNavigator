package com.andronest.repository

import android.util.Log
import com.andronest.di.TransportNavigatorApp
import com.andronest.model.ArrivalsResponse
import com.andronest.model.NearbyStopsResponse
import com.andronest.model.TripResponse
import com.andronest.retrofit.ResRobotAPI
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TravelRepository @Inject constructor(
    val resRobotAPI: ResRobotAPI
) {
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

            // TESTAR
            // Debug raw response
            //println("HTTP Status: ${result.code()}")
           // println("Headers: ${result.headers()}")
            //println("Raw Body: ${result.raw().body?.string()}") // Critical for seeing actual data

           // if (result.isSuccessful) {
               // val bodyString = Gson().toJson(result.body())
                //println("Parsed Body: $bodyString")
           // }
            // TESTAR

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

