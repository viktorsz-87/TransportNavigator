package com.andronest.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.andronest.navigation.BottomNavigationBar
import com.andronest.screens.search.EmptyState
import com.andronest.screens.shared.ErrorState
import com.andronest.screens.shared.SearchingState
import com.andronest.viewmodels.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onArrivals: () -> Unit,
    onTrip: () -> Unit,
    onSearch: () -> Unit,
    currentDest: String?,
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.favoritesUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Favorites") }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentDest,
                onTrip = onTrip,
                onArrivals = onArrivals,
                onSearch = onSearch
            )
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
                    FavoritesResult(
                        navController = navController,
                        results = uiState.results
                    )
                else -> EmptyState()
            }
        }
    }
}