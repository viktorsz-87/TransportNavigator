package com.andronest.screens.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.model.response.NearbyStopsResponse
import com.andronest.navigation.Navigation
import com.andronest.viewmodels.FavoritesViewModel

@Composable
fun SearchResults(
    viewModel: FavoritesViewModel,
    navController: NavController,
    results: List<NearbyStopsResponse.StopLocationOrCoordLocation>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(items = results) { stopLocation ->

            stopLocation.stopLocation?.let { stopLoc ->
                SearchCard(
                    viewModel = viewModel,
                    stopLocation = stopLoc,
                    onClick = { navController.navigate(Navigation.ArrivalsScreen.createRoute(stopLoc.extId)) }
                )
            }
        }
    }
}