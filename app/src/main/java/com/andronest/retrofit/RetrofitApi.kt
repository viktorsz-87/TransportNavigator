package com.andronest.retrofit

import com.andronest.constants.Constants.API_KEYS.RESROBOT_v2_1_KEY
import com.andronest.model.NearbyStopsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface RetrofitApi {


    @GET("v2.1/location.nearbystops")
    suspend fun getNearbyStops(
        @Query("originCoordLat") latitude: String,
        @Query("originCoordLong") longitude: String,
        @Query("format") format: String = "json",
        @Query("lang") lang: String = "en",
        @Query("accessId") apiKey: String = RESROBOT_v2_1_KEY
    ): NearbyStopsResponse

}