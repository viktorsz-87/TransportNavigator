package com.andronest.model

import com.google.gson.annotations.SerializedName

data class TripResponse(
    @SerializedName("dialectVersion")
    val dialectVersion: String = "",
    @SerializedName("planRtTs")
    val planRtTs: String = "",
    @SerializedName("requestId")
    val requestId: String = "",
    @SerializedName("ResultStatus")
    val resultStatus: ResultStatus = ResultStatus(),
    @SerializedName("scrB")
    val scrB: String = "",
    @SerializedName("scrF")
    val scrF: String = "",
    @SerializedName("serverVersion")
    val serverVersion: String = "",
    @SerializedName("TechnicalMessages")
    val technicalMessages: TechnicalMessages = TechnicalMessages(),
    @SerializedName("Trip")
    val trip: List<Trip> = listOf()
) {
    data class ResultStatus(
        @SerializedName("timeDiffCritical")
        val timeDiffCritical: Boolean = false
    )

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

    data class Trip(
        @SerializedName("calculation")
        val calculation: String = "",
        @SerializedName("checksum")
        val checksum: String = "",
        @SerializedName("ctxRecon")
        val ctxRecon: String = "",
        @SerializedName("Destination")
        val destination: Destination = Destination(),
        @SerializedName("duration")
        val duration: String = "",
        @SerializedName("idx")
        val idx: Int = 0,
        @SerializedName("LegList")
        val legList: LegList = LegList(),
        @SerializedName("Notes")
        val notes: Notes? = null,
        @SerializedName("Origin")
        val origin: Origin = Origin(),
        @SerializedName("rtDuration")
        val rtDuration: String = "",
        @SerializedName("ServiceDays")
        val serviceDays: List<ServiceDay> = listOf(),
        @SerializedName("transferCount")
        val transferCount: Int? = null,
        @SerializedName("tripId")
        val tripId: String = "",
        @SerializedName("TripStatus")
        val tripStatus: TripStatus = TripStatus()
    )  {
        data class Destination(
            @SerializedName("date")
            val date: String = "",
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
            @SerializedName("prognosisType")
            val prognosisType: String = "",
            @SerializedName("routeIdx")
            val routeIdx: Int = 0,
            @SerializedName("time")
            val time: String = "",
            @SerializedName("type")
            val type: String = ""
        )

        data class LegList(
            @SerializedName("Leg")
            val leg: List<Leg> = listOf()
        ) {
            data class Leg(
                @SerializedName("category")
                val category: String? = null,
                @SerializedName("Destination")
                val destination: Destination = Destination(),
                @SerializedName("direction")
                val direction: String? = null,
                @SerializedName("directionFlag")
                val directionFlag: String? = null,
                @SerializedName("dist")
                val dist: Int? = null,
                @SerializedName("duration")
                val duration: String = "",
                @SerializedName("GisRef")
                val gisRef: GisRef? = null,
                @SerializedName("GisRoute")
                val gisRoute: GisRoute? = null,
                @SerializedName("id")
                val id: String = "",
                @SerializedName("idx")
                val idx: Int = 0,
                @SerializedName("JourneyDetail")
                val journeyDetail: JourneyDetail? = null,
                @SerializedName("JourneyDetailRef")
                val journeyDetailRef: JourneyDetailRef? = null,
                @SerializedName("JourneyStatus")
                val journeyStatus: String? = null,
                @SerializedName("minimumChangeDuration")
                val minimumChangeDuration: String? = null,
                @SerializedName("name")
                val name: String = "",
                @SerializedName("Notes")
                val notes: Notes? = null,
                @SerializedName("number")
                val number: String? = null,
                @SerializedName("Origin")
                val origin: Origin = Origin(),
                @SerializedName("Product")
                val product: List<Product> = listOf(),
                @SerializedName("reachable")
                val reachable: Boolean? = null,
                @SerializedName("Stops")
                val stops: Stops? = null,
                @SerializedName("type")
                val type: String = "",
                @SerializedName("waitingState")
                val waitingState: String? = null
            ) {
                data class Destination(
                    @SerializedName("date")
                    val date: String = "",
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
                    @SerializedName("prognosisType")
                    val prognosisType: String? = null,
                    @SerializedName("routeIdx")
                    val routeIdx: Int? = null,
                    @SerializedName("time")
                    val time: String = "",
                    @SerializedName("type")
                    val type: String = ""
                )

                data class GisRef(
                    @SerializedName("ref")
                    val ref: String = ""
                )

                data class GisRoute(
                    @SerializedName("dirGeo")
                    val dirGeo: Int = 0,
                    @SerializedName("dist")
                    val dist: Int = 0,
                    @SerializedName("durS")
                    val durS: String = ""
                )

                data class JourneyDetail(
                    @SerializedName("dayOfOperation")
                    val dayOfOperation: String = "",
                    @SerializedName("ref")
                    val ref: String = ""
                )

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

                data class Origin(
                    @SerializedName("date")
                    val date: String = "",
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
                    @SerializedName("prognosisType")
                    val prognosisType: String? = null,
                    @SerializedName("routeIdx")
                    val routeIdx: Int? = null,
                    @SerializedName("time")
                    val time: String = "",
                    @SerializedName("type")
                    val type: String = ""
                )

                data class Product(
                    @SerializedName("admin")
                    val admin: String? = null,
                    @SerializedName("catCode")
                    val catCode: String? = null,
                    @SerializedName("catIn")
                    val catIn: String? = null,
                    @SerializedName("catOut")
                    val catOut: String? = null,
                    @SerializedName("catOutL")
                    val catOutL: String? = null,
                    @SerializedName("catOutS")
                    val catOutS: String? = null,
                    @SerializedName("cls")
                    val cls: String? = null,
                    @SerializedName("displayNumber")
                    val displayNumber: String? = null,
                    @SerializedName("icon")
                    val icon: Icon = Icon(),
                    @SerializedName("internalName")
                    val internalName: String = "",
                    @SerializedName("line")
                    val line: String? = null,
                    @SerializedName("lineId")
                    val lineId: String? = null,
                    @SerializedName("matchId")
                    val matchId: String? = null,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("num")
                    val num: String? = null,
                    @SerializedName("operator")
                    val operator: String? = null,
                    @SerializedName("operatorCode")
                    val operatorCode: String? = null,
                    @SerializedName("operatorInfo")
                    val operatorInfo: OperatorInfo? = null,
                    @SerializedName("routeIdxFrom")
                    val routeIdxFrom: Int? = null,
                    @SerializedName("routeIdxTo")
                    val routeIdxTo: Int? = null
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

                data class Stops(
                    @SerializedName("Stop")
                    val stop: List<Stop> = listOf()
                ) {
                    data class Stop(
                        @SerializedName("alighting")
                        val alighting: Boolean? = null,
                        @SerializedName("arrDate")
                        val arrDate: String? = null,
                        @SerializedName("arrPrognosisType")
                        val arrPrognosisType: String? = null,
                        @SerializedName("arrTime")
                        val arrTime: String? = null,
                        @SerializedName("boarding")
                        val boarding: Boolean? = null,
                        @SerializedName("depDate")
                        val depDate: String? = null,
                        @SerializedName("depDir")
                        val depDir: String? = null,
                        @SerializedName("depPrognosisType")
                        val depPrognosisType: String? = null,
                        @SerializedName("depTime")
                        val depTime: String? = null,
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
                        @SerializedName("Notes")
                        val notes: Notes? = null,
                        @SerializedName("routeIdx")
                        val routeIdx: Int = 0
                    ) {
                        data class Notes(
                            @SerializedName("Note")
                            val note: List<Note> = listOf()
                        ) {
                            data class Note(
                                @SerializedName("key")
                                val key: String = "",
                                @SerializedName("txtN")
                                val txtN: String = "",
                                @SerializedName("type")
                                val type: String = "",
                                @SerializedName("value")
                                val value: String = ""
                            )
                        }
                    }
                }
            }
        }

        data class Notes(
            @SerializedName("Note")
            val note: List<Note> = listOf()
        ) {
            data class Note(
                @SerializedName("key")
                val key: String = "",
                @SerializedName("txtN")
                val txtN: String = "",
                @SerializedName("type")
                val type: String = "",
                @SerializedName("value")
                val value: String = ""
            )
        }

        data class Origin(
            @SerializedName("date")
            val date: String = "",
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
            @SerializedName("prognosisType")
            val prognosisType: String? = null,
            @SerializedName("routeIdx")
            val routeIdx: Int? = null,
            @SerializedName("time")
            val time: String = "",
            @SerializedName("type")
            val type: String = ""
        )

        data class ServiceDay(
            @SerializedName("planningPeriodBegin")
            val planningPeriodBegin: String = "",
            @SerializedName("planningPeriodEnd")
            val planningPeriodEnd: String = "",
            @SerializedName("sDaysB")
            val sDaysB: String = "",
            @SerializedName("sDaysI")
            val sDaysI: String = "",
            @SerializedName("sDaysR")
            val sDaysR: String = ""
        )

        data class TripStatus(
            @SerializedName("convenient")
            val convenient: Boolean? = null,
            @SerializedName("detour")
            val detour: Boolean? = null,
            @SerializedName("economic")
            val economic: Boolean? = null,
            @SerializedName("hintCode")
            val hintCode: Int = 0,
            @SerializedName("unsharp")
            val unsharp: Boolean? = null
        )
    }
}


