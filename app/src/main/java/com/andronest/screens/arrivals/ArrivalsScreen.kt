package com.andronest.screens.arrivals

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
import com.andronest.navigation.BottomNavigationBar
import com.andronest.screens.search.EmptyState
import com.andronest.screens.shared.ErrorState
import com.andronest.screens.shared.SearchingState
import com.andronest.viewmodels.ArrivalsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArrivalsScreen(
    onSearch: () -> Unit,
    onTrip: () -> Unit,
    onFavorites: () -> Unit,
    currentDest: String?,
    id: String,
    navController: NavController,
    viewModel: ArrivalsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.arrivalsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.errorChannel.collect { errorMsg ->
            snackbarHostState.showSnackbar(errorMsg)
        }
    }
    LaunchedEffect(key1 = id) {
        viewModel.getArrivals(id = id)
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
                title = { Text("Arrivals") }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentDest,
                onSearch = onSearch,
                onTrip = onTrip,
                onFavorites = onFavorites
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when {
                uiState.errorMessage != null -> ErrorState(errorMessage = uiState.errorMessage)
                uiState.isSearching -> SearchingState()
                !uiState.results.isNullOrEmpty() ->
                    ArrivalsResult(
                        viewModel = viewModel,
                        navController = navController,
                        results = uiState.results
                    )

                else -> EmptyState()
            }
        }
    }
}
