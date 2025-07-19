package com.andronest.model.state

import com.andronest.model.response.TripResponse

data class TripScreenState(
    val isSearching: Boolean=false,
    val errorMessage: String?=null,
    val results: List<TripResponse.Trip> = emptyList()
)