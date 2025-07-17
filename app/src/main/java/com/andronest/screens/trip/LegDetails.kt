package com.andronest.screens.trip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andronest.R
import com.andronest.model.TripResponse.Trip.LegList.Leg
import com.andronest.model.TripResponse.Trip.LegList.Leg.Stops.Stop


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegDetails(
    leg: Leg,
    legNumber: Int,
    modifier: Modifier = Modifier,
    onStopClick: (Stop) -> Unit
) {
    val transportIcon = when (leg.category?.uppercase()) {
        "BUS", "BLT","BBL","BRB","BRE"                                  -> painterResource(R.drawable.bus_24)
        "METRO", "SUBWAY", "ULT"                                        -> painterResource(R.drawable.subway_24)
        "TRAIN","JBL","JLT","JRE","JEN","JNT","JST","JEX","REG","JAX"   -> painterResource(R.drawable.train_24)
        "TRAM","SLT"                                                    -> painterResource(R.drawable.tram_24)
        "WALK"                                                          -> painterResource(R.drawable.walk_24)
        "JNY"                                                           -> painterResource(R.drawable.location_on_24)
        else                                                            -> { painterResource(R.drawable.walk_24) }
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
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = leg.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = formatTimeDuration(leg.duration),
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