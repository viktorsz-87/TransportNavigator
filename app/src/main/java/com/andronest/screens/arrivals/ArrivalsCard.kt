package com.andronest.screens.arrivals

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.model.ArrivalsResponse
import com.andronest.navigation.Navigation
import com.andronest.viewmodels.ArrivalsViewModel

@Composable
fun ArrivalsCard(
    navController: NavController,
    item: ArrivalsResponse.Arrival,
    viewModel: ArrivalsViewModel,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier.fillMaxWidth()) {

        Card(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            shape = RectangleShape,
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
                .clickable(
                    enabled = true,
                    onClick = { navController.navigate(Navigation.TripScreen.createRoute(item.lat, item.lon, item.stopid)) })
        ) {

            ArrivalsCardItem(item = item)
        }
    }
}