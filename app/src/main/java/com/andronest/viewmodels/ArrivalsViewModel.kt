package com.andronest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.ArrivalsScreenState
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
class ArrivalsViewModel @Inject constructor(
    private val repository: TravelRepository
) : ViewModel() {

    private val _errorChannel: Channel<String> = Channel<String>()
    val errorChannel = _errorChannel.receiveAsFlow()

    private val _arrivalsUiState = MutableStateFlow(ArrivalsScreenState())
    val arrivalsUiState = _arrivalsUiState.asStateFlow()

    fun getArrivals(id: String) {
        viewModelScope.launch {

            _arrivalsUiState.update {
                it.copy(isSearching = true, errorMessage = null, results = emptyList())
            }

            try {
                val result = repository.getArrivals(id = id)
                _arrivalsUiState.update {
                    it.copy(
                        isSearching = false,
                        errorMessage = if(result.isNullOrEmpty()) "Error getting arrivals" else null,
                        results = result)
                }

            } catch (e: Exception){
                _arrivalsUiState.update {
                    it.copy(
                        isSearching = false,
                        errorMessage = e.message ?: "Unknown error",
                        results = emptyList())
                }
                _errorChannel.send(e.message?: "Unknown error")
            }
        }
    }
}