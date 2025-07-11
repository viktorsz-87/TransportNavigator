package com.andronest.retrofit

import com.andronest.constants.Constants.API_KEYS.RES_ROBOT_v2_1_KEY
import com.andronest.model.NearbyStopsResponse
import retrofit.http.GET
import retrofit.http.Query


interface RetrofitApi {

    @GET("location.nearbystops")
    fun getNearbyStops(
        @Query("originCoordLong") longitude: String,
        @Query("originCoordLat") latitude: String,
        @Query("accessId") apiKey: String = RES_ROBOT_v2_1_KEY,
        @Query("format") format: String = "json",
        @Query("lang") lang: String = "en"
    ): NearbyStopsResponse

}