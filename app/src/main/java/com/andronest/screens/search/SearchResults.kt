package com.andronest.screens.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.model.NearbyStopsResponse
import com.andronest.navigation.Navigation

@Composable
fun SearchResults(
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
                    stopLocation = stopLoc,
                    onClick = { navController.navigate(Navigation.ArrivalsScreen.createRoute(stopLoc.id)) })
            }
        }
    }
}