package com.andronest.retrofit


import com.andronest.constants.Constants.API_KEYS.RESROBOT_API_KEY
import com.andronest.model.NearbyStopsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ResRobotAPI {

    @GET("v2.1/location.nearbystops")
    suspend fun getNearbyStops(
        @Query("originCoordLat") latitude: String = "59.4",
        @Query("originCoordLong") longitude: String = "18.1",
        @Query("format") format: String = "json",
        @Query("r") r: Int = 2000,                              // maxDistance
        @Query("maxNo") maxResults: Int = 10,
        @Query("lang") language: String="en",
        //@Query("products") transportType: Int = 128,          // 128 (bus), 32 (subway)
        @Query("accessId") apiKey: String = RESROBOT_API_KEY
    ): Response<NearbyStopsResponse> // Use Retrofit's Response wrapper

    @GET("v2.1/departureBoard")
    suspend fun getDepartures(
        @Query("id") id: String,
        @Query("lang") language: String="en",
        @Query("format") format: String = "json",
        @Query("maxJourneys") maxResults: Int = 5,
        @Query("accessId") apiKey: String = RESROBOT_API_KEY
    ): Response<NearbyStopsResponse> // Use Retrofit's Response wrapper

    @GET("v2.1/arrivalBoard")
    suspend fun getArrivals(
        @Query("id") id: String,
        @Query("lang") language: String="en",
        @Query("format") format: String = "json",
        @Query("maxJourneys") maxResults: Int = 5,
        @Query("accessId") apiKey: String = RESROBOT_API_KEY
    ): Response<NearbyStopsResponse> // Use Retrofit's Response wrapper

}

