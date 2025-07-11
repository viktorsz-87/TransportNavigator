package com.andronest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.SearchScreenState
import com.andronest.repository.TravelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: TravelRepository
) : ViewModel() {

    private val _errorChannel = Channel<String>()
    val errorChannel = _errorChannel.receiveAsFlow()

    private val _searchUiState = MutableStateFlow<SearchScreenState>(SearchScreenState())
    val searchUiState = _searchUiState.asStateFlow()

    init {
        getNearbyStops()
    }

     fun getNearbyStops() {
        viewModelScope.launch {

            _searchUiState.update { it.copy(isSearching = true, errorMessage = null) }

            try {
                val result = repository.getNearbyStops()

                _searchUiState.update {
                   it.copy(
                       results = result,
                       errorMessage = if(result.isNullOrEmpty()) "No stops found" else null,
                       isSearching = false)
                }
            } catch (e: Exception) {
                _searchUiState.update {
                    it.copy(
                        results = emptyList(),
                        errorMessage = e.message ?: "Unknown error",
                        isSearching = false)
                }
                _errorChannel.send(e.message ?: "Unknown error")
            }
        }
    }
}