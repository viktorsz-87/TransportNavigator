package com.andronest.repository

import android.util.Log
import com.andronest.model.StopLocationOrCoordLocation
import com.andronest.retrofit.RetrofitApi
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TravelRepository @Inject constructor(
    val retrofitApi: RetrofitApi){


    suspend fun getNearbyStops(): List<StopLocationOrCoordLocation> {

        try {
            val result = retrofitApi.getNearbyStops("57.708895","11.973479").stopLocationOrCoordLocation

            return result


        }catch (e: Exception){
            Log.d("MyTag", "error: " + e.message)
            return emptyList()
        }
    }
}