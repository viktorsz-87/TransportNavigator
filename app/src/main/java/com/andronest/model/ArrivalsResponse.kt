package com.andronest.model

import com.google.gson.annotations.SerializedName

data class ArrivalsResponse(
    @SerializedName("Arrival")
    val arrival: List<Arrival> = listOf(),
    @SerializedName("dialectVersion")
    val dialectVersion: String = "",
    @SerializedName("planRtTs")
    val planRtTs: String = "",
    @SerializedName("requestId")
    val requestId: String = "",
    @SerializedName("serverVersion")
    val serverVersion: String = "",
    @SerializedName("TechnicalMessages")
    val technicalMessages: TechnicalMessages = TechnicalMessages()
) {
    data class Arrival(
        @SerializedName("date")
        val date: String = "",
        @SerializedName("JourneyDetailRef")
        val journeyDetailRef: JourneyDetailRef = JourneyDetailRef(),
        @SerializedName("JourneyStatus")
        val journeyStatus: String = "",
        @SerializedName("lat")
        val lat: Double = 0.0,
        @SerializedName("lon")
        val lon: Double = 0.0,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("Notes")
        val notes: Notes = Notes(),
        @SerializedName("origin")
        val origin: String = "",
        @SerializedName("Product")
        val product: List<Product> = listOf(),
        @SerializedName("ProductAtStop")
        val productAtStop: ProductAtStop = ProductAtStop(),
        @SerializedName("reachable")
        val reachable: Boolean = false,
        @SerializedName("stop")
        val stop: String = "",
        @SerializedName("stopExtId")
        val stopExtId: String = "",
        @SerializedName("stopid")
        val stopid: String = "",
        @SerializedName("time")
        val time: String = "",
        @SerializedName("type")
        val type: String = ""
    ) {
        data class JourneyDetailRef(
            @SerializedName("ref")
            val ref: String = ""
        )

        data class Notes(
            @SerializedName("Note")
            val note: List<Note> = listOf()
        ) {
            data class Note(
                @SerializedName("key")
                val key: String = "",
                @SerializedName("routeIdxFrom")
                val routeIdxFrom: Int = 0,
                @SerializedName("routeIdxTo")
                val routeIdxTo: Int = 0,
                @SerializedName("txtN")
                val txtN: String = "",
                @SerializedName("type")
                val type: String = "",
                @SerializedName("value")
                val value: String = ""
            )
        }

        data class Product(
            @SerializedName("admin")
            val admin: String = "",
            @SerializedName("catCode")
            val catCode: String = "",
            @SerializedName("catIn")
            val catIn: String = "",
            @SerializedName("catOut")
            val catOut: String = "",
            @SerializedName("catOutL")
            val catOutL: String = "",
            @SerializedName("catOutS")
            val catOutS: String = "",
            @SerializedName("cls")
            val cls: String = "",
            @SerializedName("displayNumber")
            val displayNumber: String = "",
            @SerializedName("icon")
            val icon: Icon = Icon(),
            @SerializedName("internalName")
            val internalName: String = "",
            @SerializedName("line")
            val line: String = "",
            @SerializedName("lineId")
            val lineId: String = "",
            @SerializedName("matchId")
            val matchId: String = "",
            @SerializedName("name")
            val name: String = "",
            @SerializedName("num")
            val num: String = "",
            @SerializedName("operator")
            val operator: String = "",
            @SerializedName("operatorCode")
            val operatorCode: String = "",
            @SerializedName("operatorInfo")
            val operatorInfo: OperatorInfo = OperatorInfo(),
            @SerializedName("routeIdxFrom")
            val routeIdxFrom: Int = 0,
            @SerializedName("routeIdxTo")
            val routeIdxTo: Int = 0
        ) {
            data class Icon(
                @SerializedName("res")
                val res: String = ""
            )

            data class OperatorInfo(
                @SerializedName("id")
                val id: String = "",
                @SerializedName("name")
                val name: String = "",
                @SerializedName("nameL")
                val nameL: String = "",
                @SerializedName("nameN")
                val nameN: String = "",
                @SerializedName("nameS")
                val nameS: String = ""
            )
        }

        data class ProductAtStop(
            @SerializedName("admin")
            val admin: String = "",
            @SerializedName("catCode")
            val catCode: String = "",
            @SerializedName("catIn")
            val catIn: String = "",
            @SerializedName("catOut")
            val catOut: String = "",
            @SerializedName("catOutL")
            val catOutL: String = "",
            @SerializedName("catOutS")
            val catOutS: String = "",
            @SerializedName("cls")
            val cls: String = "",
            @SerializedName("displayNumber")
            val displayNumber: String = "",
            @SerializedName("icon")
            val icon: Icon = Icon(),
            @SerializedName("internalName")
            val internalName: String = "",
            @SerializedName("line")
            val line: String = "",
            @SerializedName("lineId")
            val lineId: String = "",
            @SerializedName("matchId")
            val matchId: String = "",
            @SerializedName("name")
            val name: String = "",
            @SerializedName("num")
            val num: String = "",
            @SerializedName("operator")
            val operator: String = "",
            @SerializedName("operatorCode")
            val operatorCode: String = "",
            @SerializedName("operatorInfo")
            val operatorInfo: OperatorInfo = OperatorInfo()
        ) {
            data class Icon(
                @SerializedName("res")
                val res: String = ""
            )

            data class OperatorInfo(
                @SerializedName("id")
                val id: String = "",
                @SerializedName("name")
                val name: String = "",
                @SerializedName("nameL")
                val nameL: String = "",
                @SerializedName("nameN")
                val nameN: String = "",
                @SerializedName("nameS")
                val nameS: String = ""
            )
        }
    }

    data class TechnicalMessages(
        @SerializedName("TechnicalMessage")
        val technicalMessage: List<TechnicalMessage> = listOf()
    ) {
        data class TechnicalMessage(
            @SerializedName("key")
            val key: String = "",
            @SerializedName("value")
            val value: String = ""
        )
    }
}