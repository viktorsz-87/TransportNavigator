package com.andronest.screens.trip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andronest.model.TripResponse
import com.andronest.model.TripResponse.Trip.LegList.Leg.Stops.Stop


@Composable
fun TripDetails(
    trip: TripResponse.Trip,
    onStopClick: (Stop) -> Unit,
    modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Show each leg of the journey
        trip.legList.leg.forEachIndexed { index, leg ->
            LegDetails(
                leg = leg,
                legNumber = index + 1,
                onStopClick = onStopClick
            )
        }
    }
}