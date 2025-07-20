package com.andronest.screens.favorites

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.room.FavoriteStopEntity
import com.andronest.viewmodels.FavoritesViewModel

@Composable
fun FavoritesResult(
    navController: NavController,
    results: List<FavoriteStopEntity>,
    viewModel: FavoritesViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        items(items = results) { item ->

            FavoritesCard(
                item = item,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}