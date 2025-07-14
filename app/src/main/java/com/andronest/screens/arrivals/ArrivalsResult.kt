package com.andronest.screens.arrivals

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.model.ArrivalsResponse

@Composable
fun ArrivalsResult(
    navController: NavController,
    results: List<ArrivalsResponse.Arrival>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {

        items(items = results) { item ->

          ArrivalsCard(item=item)

        }
    }
}