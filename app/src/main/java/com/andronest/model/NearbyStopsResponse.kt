package com.andronest.model

import android.graphics.drawable.Icon
import com.andronest.constants.Constants
import com.google.gson.annotations.SerializedName


data class NearbyStopsResponse(
    @SerializedName("stopLocationOrCoordLocation")
    val stops: List<StopLocationWrapper> = listOf(),

    @SerializedName("TechnicalMessages")
    val technicalMessages: TechnicalMessages? = null,

    @SerializedName("serverVersion")
    val serverVersion: String? = null,

    @SerializedName("dialectVersion")
    val dialectVersion: String? = null,

    @SerializedName("requestId")
    val requestId: String? = null
) {
    // Helper property to easily access stops without nesting
    val allStops: List<StopLocation>
        get() = stops.mapNotNull { it.stopLocation }
}

data class StopLocationWrapper(
    @SerializedName("StopLocation")
    val stopLocation: StopLocation? = null
)

data class StopLocation(
    @SerializedName("id") val id: String,
    @SerializedName("extId") val extId: String,
    @SerializedName("name") val name: String,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("weight") val weight: Int,
    @SerializedName("dist") val distance: Int, // Distance in meters
    @SerializedName("products") val products: Int,
    @SerializedName("timezoneOffset") val timezoneOffset: Int? = null,
    @SerializedName("minimumChangeDuration") val minChangeDuration: String? = null,

    @SerializedName("productAtStop")
    val productAtStop: List<ProductAtStop>? = null
)

data class ProductAtStop(
    @SerializedName("cls") val cls: String? = null,
    @SerializedName("icon") val icon: Icon? = null,
){
    fun getProductType(): String {
        return Constants.ResRobot.productTypes[cls] ?: "Unknown"
    }
}

data class Icon(
    @SerializedName("res") val resource: String? = null
)

data class TechnicalMessages(
    @SerializedName("TechnicalMessage")
    val messages: List<TechnicalMessage>? = null
)

data class TechnicalMessage(
    @SerializedName("key") val key: String? = null,
    @SerializedName("value") val value: String? = null
)