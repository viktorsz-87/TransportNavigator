package com.andronest.screens.trip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.model.TripResponse

@Composable
fun TripResult(
    navController: NavController,
    trips: List<TripResponse.Trip>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(trips.size) {index->

            TripCard(
                navController = navController,
                trip= trips[index],
            )
            //debugTrip(results[index])
        }
    }
}

