package com.andronest.screens.trip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.andronest.di.TransportNavigatorApp
import com.andronest.navigation.BottomNavigationBar
import com.andronest.screens.search.EmptyState
import com.andronest.screens.shared.ErrorState
import com.andronest.screens.shared.SearchingState
import com.andronest.viewmodels.TripViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripScreen(
    stopId: String,
    currentDest: String?,
    navController: NavController,
    viewModel: TripViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.tripUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.errorChannel.collect { errorMessage ->
            snackbarHostState.showSnackbar(message = errorMessage)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getTrip(
            destStopId = stopId,
            originStopId = TransportNavigatorApp.userStopId
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Trips") }
            )
        },
        bottomBar = {
            BottomNavigationBar(currentDest, onClick = {})
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when {
                uiState.errorMessage != null -> ErrorState(uiState.errorMessage)
                uiState.isSearching -> SearchingState()
                !uiState.results.isNullOrEmpty() ->
                    /*TripResults(
                        navController = navController,
                        results = uiState.results
                    )*/
                    MapWithMarkers()

                else -> EmptyState()
            }
        }
    }
}