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
import com.andronest.viewmodels.ArrivalsViewModel

@Composable
fun ArrivalsResult(
    navController: NavController,
    results: List<ArrivalsResponse.Arrival>,
    viewModel: ArrivalsViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(items = results) { item ->

            ArrivalsCard(
                viewModel = viewModel,
                item = item,
                navController = navController
            )

        }
    }
}