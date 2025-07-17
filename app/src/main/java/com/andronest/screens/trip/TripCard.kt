package com.andronest.screens.trip

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andronest.model.TripResponse
import com.andronest.model.TripResponse.Trip.LegList.Leg.Stops.Stop


@Composable
fun TripCard(
    trip: TripResponse.Trip,
    onStopClick:(Stop) -> Unit = {},
    modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {

        Column(modifier=modifier
            .fillMaxWidth()
            .padding(12.dp)
            .animateContentSize()) {

            TripCardSummary(
                originName = trip.origin.name,
                destinationName = trip.destination.name,
                duration = formatTimeDuration(trip.duration),
                departure = trip.origin.time,
                arrival = trip.destination.time
            )

            // Expand / collapse button
            IconButton(
                onClick = {expanded = !expanded },
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(
                    imageVector = if(expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = if(expanded) "Collapse details" else "Show details")
            }

            if(expanded){
                TripDetails(trip=trip, onStopClick= onStopClick)
            }
        }
    }
}

fun formatTimeDuration(duration: String): String {
    if (duration == "PT") return "0 mins"  // Handle empty duration

    var rest = duration.substring(2)  // Remove "PT"
    var minutes = 0
    var hours = 0

    // Extract hours
    if (rest.contains("H")) {
        val parts = rest.split("H", limit = 2)
        hours = parts[0].toIntOrNull() ?: 0
        rest = parts.getOrNull(1) ?: ""
    }

    // Extract minutes
    if (rest.contains("M")) {
        val parts = rest.split("M", limit = 2)
        minutes = parts[0].toIntOrNull() ?: 0
    }

    // Convert to total minutes if hours exist
    val totalMinutes = hours * 60 + minutes

    return when {
        hours > 0 && minutes > 0 -> "$hours h ${minutes} mins"
        hours > 0 -> "$hours h"
        else -> "$totalMinutes mins"
    }
}