package com.andronest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.TripScreenState
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
class TripViewModel @Inject constructor(
    private val repository: TravelRepository
) : ViewModel() {

    private val _errorChannel: Channel<String> = Channel<String>()
    val errorChannel = _errorChannel.receiveAsFlow()

    private val _tripUiState = MutableStateFlow(TripScreenState())
    val tripUiState = _tripUiState.asStateFlow()

    fun getTrip(originStopId: String, destStopId: String)
    {
        viewModelScope.launch {
            _tripUiState.update { it.copy(isSearching = true) }

            try {
                val result = repository.getTripRoute(originStopId = originStopId, destStopId = destStopId)

                _tripUiState.update {
                    it.copy(
                        isSearching = false,
                        results = result,
                        errorMessage = if (result.isNullOrEmpty()) "Error getting trip info" else null
                    )
                }
            } catch (e: Exception) {
                _tripUiState.update {
                    it.copy(
                        results = emptyList(),
                        errorMessage = e.message ?: "Unknown error",
                        isSearching = false
                    )
                }
                _errorChannel.send(e.message ?: "Unknown error")
            }
        }
    }
}