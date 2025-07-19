package com.andronest.model.state

import com.andronest.model.response.ArrivalsResponse

data class ArrivalsScreenState(
    val isSearching: Boolean=false,
    val errorMessage: String?=null,
    val results: List<ArrivalsResponse.Arrival> = emptyList()
)
