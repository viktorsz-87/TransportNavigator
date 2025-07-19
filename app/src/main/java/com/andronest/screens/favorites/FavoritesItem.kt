package com.andronest.screens.favorites

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.andronest.room.FavoriteStopEntity

@Composable
fun FavoritesItem(
    item: FavoriteStopEntity,
    navController: NavController,
    modifier: Modifier = Modifier) {

    Row(modifier = modifier.fillMaxWidth()) {

        Text(text = "Name: ${item.name}")
        Text(text = "Latitude: ${item.latitude}")
        Text(text = "Longitude: ${item.longitude}")
    }
}