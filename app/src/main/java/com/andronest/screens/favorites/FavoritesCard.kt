package com.andronest.screens.favorites

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.room.FavoriteStopEntity

@Composable
fun FavoritesCard(
    item: FavoriteStopEntity,
    navController: NavController,
    modifier: Modifier = Modifier) {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(),
        shape = CardDefaults.elevatedShape,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        FavoritesItem(item=item, navController=navController)
    }
}