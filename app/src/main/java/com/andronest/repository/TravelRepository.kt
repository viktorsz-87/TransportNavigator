package com.andronest.repository

import android.util.Log
import com.andronest.model.StopLocation
import com.andronest.retrofit.RetrofitApi
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TravelRepository @Inject constructor(
    val retrofitApi: RetrofitApi){


    suspend fun getNearbyStops(): List<StopLocation>{

        try {
            var result = retrofitApi.getNearbyStops("18.083056","59.293611").stopLocations

            if(result.isNullOrEmpty()) {
                 result = emptyList()
            } else {
                return result
            }

        }catch (e: Exception){
            Log.d("MyTag", "error: " + e.message)
        }

        return emptyList()
    }

}