package com.andronest.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andronest.R
import com.andronest.model.StopLocationOrCoordLocation
import com.andronest.navigation.BottomNavigationBar
import com.andronest.viewmodels.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    currentDest: String?,
    viewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val uiState by viewModel.searchUiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.errorChannel.collect { errorMessage ->
            snackbarHostState.showSnackbar(errorMessage)
        }
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
                title = {Text("Nearby stops")}
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

            // Remember(uiState): Only when reference changes..
            remember(uiState) {
                derivedStateOf {
                    // check the state values..
                    !uiState.isSearching &&
                    uiState.errorMessage == null &&
                    !uiState.results.isNullOrEmpty()
                }
            }.value.let { showResults->

                when(showResults){
                    true -> SearchResults(uiState.results)
                    else->  when {
                        uiState.isSearching -> SearchingState()
                        uiState.errorMessage != null -> ErrorState(uiState.errorMessage)
                        else -> EmptyState()
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyState(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.LocationOn, contentDescription = null)
        Text(text = "No stops found", style = MaterialTheme.typography.titleMedium)
        Text(text = "Try moving to a different location")
    }
}

@Composable
fun SearchingState(modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun SearchResults(
    results: List<StopLocationOrCoordLocation>,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier.fillMaxSize()) {

        items(items = results) { stopLocation ->

            Text(text = "location: ${stopLocation.stopLocation.name}")
        }
    }
}

@Composable
fun ErrorState(
    errorMessage: String?,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Text(
            text = stringResource(R.string.error, errorMessage.toString()),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}