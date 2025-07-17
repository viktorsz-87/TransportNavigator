package com.andronest.screens.trip

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.andronest.R
import com.andronest.model.TripResponse
import com.andronest.model.TripResponse.Trip.LegList.Leg
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
                duration = formatDuration(trip.duration),
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

fun formatDuration(duration: String): String {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LegDetails(
    leg: Leg,
    legNumber: Int,
    modifier: Modifier = Modifier,
    onStopClick: (Stop) -> Unit
) {
    val transportIcon = when (leg.category?.uppercase()) {
        "BUS", "BLT","BBL","BRB","BRE"                                      -> painterResource(R.drawable.bus_24)
        "METRO", "SUBWAY", "ULT"                                            -> painterResource(R.drawable.subway_24)
        "TRAIN","JBL","JLT","JRE","JEN","JNT","JST","JEX","REG","JAX"       -> painterResource(R.drawable.train_24)
        "TRAM","SLT"                                                        -> painterResource(R.drawable.tram_24)
        "WALK"                                                              -> painterResource(R.drawable.walk_24)
        "JNY"                                                               -> painterResource(R.drawable.location_on_24)
        else                                                                -> {painterResource(R.drawable.walk_24)}
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Leg header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = transportIcon,
                    contentDescription = leg.type,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Step $legNumber: ",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = leg.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = formatDuration(leg.duration),
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Origin and destination
            StopPoint(
                name = leg.origin.name,
                time = leg.origin.time,
                isFirst = true
            )

            // Intermediate stops
            leg.stops?.stop?.forEach { stop ->
                StopPoint(
                    name = stop.name,
                    time = stop.arrTime ?: stop.depTime ?: "",
                    onClick = { onStopClick(stop) }
                )
            }

            StopPoint(
                name = leg.destination.name,
                time = leg.destination.time,
                isLast = true
            )
        }
    }
}

@Composable
private fun StopPoint(
    name: String,
    time: String,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    onClick: () -> Unit = {}
) {
    val lineColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    val dotSize = 8.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Vertical line connector
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(if (isLast) dotSize else 28.dp)
                .padding(horizontal = 4.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            if (!isFirst) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        color = lineColor,
                        start = Offset(size.width / 2, 0f),
                        end = Offset(size.width / 2, size.height),
                        strokeWidth = 2.dp.toPx()
                    )
                }
            }
            // Dot indicator
            Canvas(modifier = Modifier.size(dotSize)) {
                drawCircle(
                    color = lineColor,
                    radius = dotSize.toPx() / 2
                )
            }
        }

        // Stop info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (time.isNotBlank()) {
                Text(
                    text = time,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
        }
    }
}
