package com.andronest.model


data class SearchScreenState(
    val isSearching: Boolean=false,
    val errorMessage: String?=null,
    val results: List<StopLocationWrapper> = emptyList()
)