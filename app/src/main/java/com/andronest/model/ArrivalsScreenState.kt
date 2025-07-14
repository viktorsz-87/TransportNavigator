package com.andronest.model

data class ArrivalsScreenState(
    val isSearching: Boolean=false,
    val errorMessage: String?=null,
    val results: List<ArrivalsResponse.Arrival> = emptyList()
)
