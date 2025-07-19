package com.andronest.model.state

import com.andronest.room.FavoriteStopEntity

data class FavoritesState(
    val isSearching: Boolean=false,
    val errorMessage: String?=null,
    val results:List<FavoriteStopEntity> = emptyList()
)