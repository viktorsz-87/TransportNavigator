package com.andronest.model

import com.google.gson.annotations.SerializedName

data class NearbyStopsResponse(

    @SerializedName("StopLocation")
    val stopLocations: List<StopLocation>
)

data class StopLocation(
    @SerializedName("id")
    val id: String?,

    @SerializedName("extId")
    val extId: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("lon")
    val longitude: String?,

    @SerializedName("lat")
    val latitude: String?,

    @SerializedName("weight")
    val weight: String?,

    @SerializedName("products")
    val products: String?
)