package com.andronest.screens.trip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.model.TripResponse
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TripResults(
    navController: NavController,
    results: List<TripResponse.Trip>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(results.size) {

            val trip = results[it]

            /*val summary = """
            From:       ${trip.origin.name}
            To:         ${trip.destination.name}
            Lat origin 1:     ${trip.origin.lat}
            Long origin 1:    ${trip.origin.lon}
            Lat dst 1:        ${trip.destination.lat}
            Long dst 1:       ${trip.destination.lon}
            Duration:   ${trip.duration}
            Transfers:  ${trip.transferCount ?: 0}
            Departure:  ${formatTime(trip.origin.time)}
            Arrival:    ${formatTime(trip.destination.time)}

            """.trimIndent()*/

            //Text(text = summary)

            /*trip.legList.leg.forEach { leg ->

            val legInfo = """
            =============================
            Type: ${leg.type}
            Line: ${leg.product.firstOrNull()?.displayNumber ?: "N/A"}
            From: ${leg.origin.name} (${formatTime(leg.origin.time)})
            Lat origin 2:     ${trip.origin.lat}
            Long origin 2:    ${trip.origin.lon}
            Lat dst 2:        ${trip.destination.lat}
            Long dst 2:       ${trip.destination.lon}
            To: ${leg.destination.name} (${formatTime(leg.destination.time)})
            Duration: ${leg.duration}
            Geometry: ${leg.gisRoute != null}

            """.trimIndent()*/
            //Text(text = legInfo)

            // Print trip summary first
            Text(text = "\nTRIP SUMMARY\n" +
                    "From: ${trip.origin?.name ?: "Unknown"} (${trip.origin?.extId})\n" +
                    "To: ${trip.destination?.name ?: "Unknown"} (${trip.destination?.extId})\n" +
                    "Duration: ${trip.duration ?: "N/A"}\n" +
                    "Departure: ${trip.origin?.time ?: "N/A"}\n" +
                    "Arrival: ${trip.destination?.time ?: "N/A"}",
                style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold)
            )

// Print each leg with stops
            trip.legList?.leg?.forEachIndexed { legIndex, leg ->
                Column {
                    // Leg header
                    Text(text = "\nLEG ${legIndex + 1}: ${leg.type ?: "UNKNOWN TRANSPORT"}".uppercase(),
                        style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold)
                    )

                    // Origin and destination of this leg
                    Text(text = "├─ From: ${leg.origin?.name ?: "Unknown"} (${leg.origin?.extId})")
                    Text(text = "├─ To: ${leg.destination?.name ?: "Unknown"} (${leg.destination?.extId})")
                    Text(text = "├─ Duration: ${leg.duration ?: "N/A"}")

                    // Transport details if available
                    leg.product?.forEach { product ->
                        Text(text = "├─ Transport: ${product.name ?: "N/A"} (${product.num ?: "N/A"})")
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
                                    "${stop.name ?: "Unknown"} (${stop.extId})\n" +
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
    }
}


fun formatTime(timeString: String): String {
    return try {
        SimpleDateFormat("HH:mm:ss", Locale.US).parse(timeString)?.let {
            SimpleDateFormat("HH:mm", Locale.US).format(it)
        } ?: timeString
    } catch (e: Exception) {
        timeString
    }
}