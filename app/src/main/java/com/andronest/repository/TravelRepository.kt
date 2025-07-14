package com.andronest.repository

import android.util.Log
import com.andronest.model.ArrivalsResponse
import com.andronest.model.NearbyStopsResponse
import com.andronest.retrofit.ResRobotAPI
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TravelRepository @Inject constructor(
    val resRobotAPI: ResRobotAPI
) {
    suspend fun getNearbyStops(lat: String, long: String): List<NearbyStopsResponse.StopLocationOrCoordLocation> {

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

            return when{
                result.isSuccessful->result.body()?.arrival.orEmpty()
                else -> emptyList()
            }

        } catch (e: Exception){
            Log.d("TravelRepository", "error: " + e.message)
            return emptyList()
        }
    }
}