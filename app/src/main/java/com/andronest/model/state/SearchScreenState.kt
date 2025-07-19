package com.andronest.model.state

import com.andronest.model.response.NearbyStopsResponse


data class SearchScreenState(
    val isSearching: Boolean=false,
    val errorMessage: String?=null,
    val results: List<NearbyStopsResponse.StopLocationOrCoordLocation> = emptyList()
)