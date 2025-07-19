package com.andronest.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andronest.R
import com.andronest.model.response.NearbyStopsResponse.StopLocationOrCoordLocation.StopLocation
import com.andronest.ui.theme.SearchTextColor
import com.andronest.viewmodels.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCardItem(
    viewModel: FavoritesViewModel,
    stopLocation: StopLocation,
    modifier: Modifier = Modifier
) {
    val favoritesState by viewModel.favoritesUiState.collectAsStateWithLifecycle()
    val isFavorite = favoritesState.results.any { it.id == stopLocation.id }

    stopLocation.productAtStop.forEach { type ->

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxSize()
        ) {

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .weight(0.1f)
                    .fillMaxSize()
            ) {

                Icon(
                    modifier = modifier
                        .size(35.dp)
                        .clickable(
                            onClick = { viewModel.toggleFavorite(stopLocation) }
                        ),
                    imageVector = if(isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Add to favorites")
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .weight(0.12f)
                    .fillMaxSize()
            ) {

                when {
                    type.getProductType() == "Bus" -> {
                        Icon(
                            modifier = modifier.size(35.dp),
                            painter = painterResource(R.drawable.bus_24),
                            contentDescription = "Stop Type"
                        )
                    }

                    type.getProductType() == "Metro" -> {
                        Icon(
                            modifier = modifier.size(35.dp),
                            painter = painterResource(R.drawable.subway_24),
                            contentDescription = "Stop Type"
                        )
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {

                Text(
                    color = SearchTextColor,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = "${stopLocation.name}, ${stopLocation.dist}m"
                )
            }
        }
    }
}