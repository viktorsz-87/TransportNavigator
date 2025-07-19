package com.andronest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.response.NearbyStopsResponse.StopLocationOrCoordLocation.StopLocation
import com.andronest.model.state.FavoritesState
import com.andronest.repository.TravelRepository
import com.andronest.room.FavoriteStopEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: TravelRepository
): ViewModel(){

    private val _favoritesUiState = MutableStateFlow<FavoritesState>(FavoritesState())
    val favoritesUiState = _favoritesUiState.asStateFlow()

    init {
        getAllFavorites()
    }

    fun isFavorite(stopId: String): Boolean {
        return _favoritesUiState.value.results.any {
            it.id == stopId
        }
    }

    fun toggleFavorite(stop: StopLocation) {
        viewModelScope.launch {

            if (isFavorite(stop.id)) {

                repository.deleteFavorite(stop.id)
                _favoritesUiState.value = _favoritesUiState.value.copy(
                    results = _favoritesUiState.value.results.filter { it.id != stop.id }
                )

            } else {
                val newFavorite = FavoriteStopEntity(
                    id = stop.id,
                    name = stop.name,
                    latitude = stop.lat,
                    longitude = stop.lon
                )
                repository.saveToFavorites(newFavorite)

                _favoritesUiState.value = _favoritesUiState.value.copy(
                    results = _favoritesUiState.value.results + newFavorite
                )
            }
        }
    }

    fun getAllFavorites(){
        viewModelScope.launch {

            try {
                _favoritesUiState.value.copy(
                    isSearching = true,
                    errorMessage = null)

                val results = repository.getAllFavorites()

                _favoritesUiState.value.copy(
                    isSearching = false,
                    results = results,
                    errorMessage = if(results.isNullOrEmpty()) "No favorites saved" else "")

            } catch (e: Exception) {

                _favoritesUiState.value.copy(
                    isSearching = false,
                    results = emptyList(),
                    errorMessage = e.message)
            }
        }
    }
}