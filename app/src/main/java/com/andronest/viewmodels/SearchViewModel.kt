package com.andronest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.SearchScreenState
import com.andronest.repository.TravelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: TravelRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchScreenState>(SearchScreenState())

    val uiState: StateFlow<SearchScreenState> = _uiState.asStateFlow()

    fun getNearbyStops() {
        viewModelScope.launch {

            val result = repository.getNearbyStops()
            if (result.isNullOrEmpty()) {
                _uiState.value.copy(results = emptyList(), errorMessage = "Could not get data")
            } else {
                _uiState.value.copy(results = result, errorMessage = "")
            }
        }
    }
}