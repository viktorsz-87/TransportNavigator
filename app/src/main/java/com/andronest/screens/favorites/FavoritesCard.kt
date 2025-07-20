package com.andronest.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.room.FavoriteStopEntity
import com.andronest.viewmodels.FavoritesViewModel

@Composable
fun FavoritesCard(
    item: FavoriteStopEntity,
    navController: NavController,
    viewModel: FavoritesViewModel,
    modifier: Modifier = Modifier
) {
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
    ) {

        FavoritesItem(
            item = item,
            navController = navController,
            viewModel = viewModel
        )
    }
}