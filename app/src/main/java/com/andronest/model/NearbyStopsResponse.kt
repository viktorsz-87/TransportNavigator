package com.andronest.model

import com.google.gson.annotations.SerializedName

data class NearbyStopsResponse(
    @SerializedName("dialectVersion")
    val dialectVersion: String = "",
    @SerializedName("requestId")
    val requestId: String = "",
    @SerializedName("serverVersion")
    val serverVersion: String = "",
    @SerializedName("stopLocationOrCoordLocation")
    val stopLocationOrCoordLocation: List<StopLocationOrCoordLocation> = listOf(),
    @SerializedName("TechnicalMessages")
    val technicalMessages: TechnicalMessages = TechnicalMessages()
)

data class StopLocationOrCoordLocation(
    @SerializedName("StopLocation")
    val stopLocation: StopLocation = StopLocation()
)

data class StopLocation(
    @SerializedName("dist")
    val dist: Int = 0,
    @SerializedName("extId")
    val extId: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("lat")
    val lat: Double = 0.0,
    @SerializedName("lon")
    val lon: Double = 0.0,
    @SerializedName("minimumChangeDuration")
    val minimumChangeDuration: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("productAtStop")
    val productAtStop: List<ProductAtStop> = listOf(),
    @SerializedName("products")
    val products: Int = 0,
    @SerializedName("timezoneOffset")
    val timezoneOffset: Int = 0,
    @SerializedName("weight")
    val weight: Int = 0
)

data class TechnicalMessages(
    @SerializedName("TechnicalMessage")
    val technicalMessage: List<TechnicalMessage> = listOf()
)

data class TechnicalMessage(
    @SerializedName("key")
    val key: String = "",
    @SerializedName("value")
    val value: String = ""
)

data class ProductAtStop(
    @SerializedName("cls")
    val cls: String = "",
    @SerializedName("icon")
    val icon: ProductAtStopIcon = ProductAtStopIcon()
)

data class ProductAtStopIcon(
    @SerializedName("res")
    val res: String = ""
)