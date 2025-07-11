package com.andronest.model


data class SearchScreenState(
    val isSearching: Boolean=false,
    val errorMessage: String="",
    val results: List<StopLocation> = emptyList()
)