package com.andronest.model

data class TripScreenState(
    val isSearching: Boolean=false,
    val errorMessage: String?=null,
    val results: List<TripResponse.Trip> = emptyList()
)