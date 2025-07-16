package com.andronest.screens.trip

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TripCardSummary(
    originName:String, destinationName: String,
    duration: String, departure: String,
    arrival:String, modifier: Modifier = Modifier) {

    Row(modifier= Modifier.fillMaxWidth()) {
        Text(text = "From: $originName", style = MaterialTheme.typography.titleSmall)
    }
    Spacer(modifier=modifier.height(5.dp))
    Row(modifier= Modifier.fillMaxWidth()) {
        Text(text = "To: $destinationName", style = MaterialTheme.typography.titleSmall)
    }
    Spacer(modifier=modifier.height(5.dp))
    Row(modifier= Modifier.fillMaxWidth()) {
        Text(text = "Duration: $duration", style = MaterialTheme.typography.titleSmall)
    }
    Spacer(modifier=modifier.height(5.dp))
    Row(modifier= Modifier.fillMaxWidth()) {
        Text(text = "Departure: $departure", style = MaterialTheme.typography.titleSmall)
    }
    Spacer(modifier=modifier.height(5.dp))
    Row(modifier= Modifier.fillMaxWidth()) {
        Text(text = "Arrival: $arrival", style = MaterialTheme.typography.titleSmall)
    }
    Spacer(modifier=modifier.height(5.dp))
}