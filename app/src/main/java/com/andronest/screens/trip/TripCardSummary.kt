package com.andronest.screens.trip

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.R
import com.andronest.model.TripResponse
import com.andronest.navigation.Navigation


@Composable
fun TripCardSummary(
    navController: NavController,
    trip: TripResponse.Trip,
    originName:String, destinationName: String,
    duration: String, departure: String,
    arrival:String, modifier: Modifier = Modifier) {

    Row(modifier= Modifier.fillMaxWidth()) {
        Text(text = "From: $originName", style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold)
    }
    Spacer(modifier=modifier.height(5.dp))
    Row(modifier= Modifier.fillMaxWidth()) {
        Text(text = "To: $destinationName", style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold)
    }
    Spacer(modifier=modifier.height(5.dp))
    Row(modifier= Modifier.fillMaxWidth()) {
        Text(text = "Duration: $duration", style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold)
    }
    Spacer(modifier=modifier.height(5.dp))
    Row(modifier= Modifier.fillMaxWidth()) {
        Text(text = "Departure: $departure", style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold)
    }
    Spacer(modifier=modifier.height(5.dp))
    Row(modifier= Modifier.fillMaxWidth()) {
        Text(text = "Arrival: $arrival", style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold)
    }

    Spacer(modifier=modifier.height(5.dp))
    HorizontalDivider(Modifier, DividerDefaults.Thickness, color = MaterialTheme.colorScheme.primary)
    Spacer(modifier=modifier.height(5.dp))

    Row(
        horizontalArrangement = Arrangement.End,
        modifier= Modifier.fillMaxWidth()) {

        Icon(
            tint = Color(0xFF000000),
            modifier = modifier
                .clickable(true, onClick = {navController.navigate(Navigation.MapScreen.createRoute(trip))})
                .size(50.dp),
            painter = painterResource(R.drawable.map_24),
            contentDescription = "See route on map")
    }
}