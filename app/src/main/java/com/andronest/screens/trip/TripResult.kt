package com.andronest.screens.trip

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

        item {

        val trip = results.first()

        val summary = """
        From: ${trip.origin.name}
        To:  ${trip.destination.name}
        Duration:  ${trip.duration}
        Transfers:  ${trip.transferCount ?: 0}
        Departure:  ${formatTime(trip.origin.time)}
        Arrival:  ${formatTime(trip.destination.time)}
       
        """.trimIndent()
            Text(text = summary)

        trip.legList.leg.forEach { leg ->

        val legInfo = """    
        =============================
        Type: ${leg.type}
        Line: ${leg.product.firstOrNull()?.displayNumber ?: "N/A"} 
        From: ${leg.origin.name} (${formatTime(leg.origin.time)})
        To: ${leg.destination.name} (${formatTime(leg.destination.time)})
        Duration: ${leg.duration}
        Geometry: ${leg.gisRoute != null} 
        
        """.trimIndent()
        Text(text = legInfo)
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