package com.andronest.screens.trip

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.andronest.model.TripResponse


@Composable
fun debugTrip(trip: TripResponse.Trip){

    // Print trip summary first
    Text(text = "\nTRIP SUMMARY\n" +
            "From: ${trip.origin.name} (${trip.origin.extId})\n" +
            "To: ${trip.destination.name} (${trip.destination.extId})\n" +
            "Duration: ${trip.duration}\n" +
            "Departure: ${trip.origin.time}\n" +
            "Arrival: ${trip.destination.time}",
        style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold)
    )

    // Print each leg with stops
    trip.legList.leg.forEachIndexed { legIndex, leg ->

        Column {

            // Leg header
            Text(text = "\nLEG ${legIndex + 1}: ${leg.type}".uppercase(),
                style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold)
            )

            // Origin and destination of this leg
            Text(text = "├─ From: ${leg.origin.name} (${leg.origin.extId})")
            Text(text = "├─ To: ${leg.destination?.name ?: "Unknown"} (${leg.destination.extId})")
            Text(text = "├─ Duration: ${leg.duration}")

            // Transport details if available
            leg.product.forEach { product ->
                Text(text = "├─ Transport: ${product.name} (${product.num ?: "N/A"})")
            }

            // Print all stops in this leg
            leg.stops?.stop?.let { stops ->

                Text(text = "└─ Stops (${stops.size}):")

                stops.forEachIndexed { stopIndex, stop ->
                    val isFirst = stopIndex == 0
                    val isLast = stopIndex == stops.size - 1

                    val prefix = when {
                        isFirst && isLast -> "   ├─ "
                        isFirst -> "   ├─ "
                        isLast -> "   └─ "
                        else -> "   │  "
                    }

                    Text(text = prefix +
                            "${stop.name} (${stop.extId})\n" +
                            "   ${" ".repeat(5)}Arr: ${stop.arrTime ?: "--:--"} | " +
                            "Dep: ${stop.depTime ?: "--:--"}" +
                            if (stop.lat != null && stop.lon != null)
                                " | Location: (${stop.lat}, ${stop.lon})" else ""
                    )
                }
            } ?: Text(text = "└─ No stop data available")
        }
    }
}