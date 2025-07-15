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
    suspend fun getStopId(lat: String, long: String): String{
        try {
            val stopId = resRobotAPI.getNearbyStops(
                TransportNavigatorApp.userLatitude.toString(),
                TransportNavigatorApp.userLongitude.toString()
            ).body()?.stopLocationOrCoordLocation?.firstNotNullOfOrNull { it.stopLocation.id }

            return stopId?: ""
        } catch (e: Exception){
            return "Error getting Stop Id"
        }
    }

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

    suspend fun getTripRoute(originStopId: String, destStopId: String):  List<TripResponse.Trip> {
        try {
            val result = resRobotAPI.getTripRoute(originId = originStopId, destId = destStopId)

            return when{
                result.isSuccessful->result.body()?.trip.orEmpty()
                else-> emptyList()
            }

        }catch (e: Exception){
            Log.d("TravelRepository", "error: " + e.message)
            return emptyList()
        }
    }
}