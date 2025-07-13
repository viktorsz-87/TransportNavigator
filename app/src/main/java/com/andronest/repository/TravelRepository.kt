package com.andronest.repository

import android.util.Log
import com.andronest.model.StopLocationWrapper
import com.andronest.retrofit.ResRobotAPI
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TravelRepository @Inject constructor(
    val resRobotAPI: ResRobotAPI){


    suspend fun getNearbyStops(lat: String, long: String): List<StopLocationWrapper> {

        try {
            val result = resRobotAPI.getNearbyStops(latitude = lat, longitude = long)

              return  when{
                 result.isSuccessful -> result.body()?.stops.orEmpty()
                 else -> emptyList()
             }
        } catch (e: Exception){
            Log.d("TravelRepository", "error: " + e.message)
            return emptyList()
        }
    }
}