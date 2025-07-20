package com.andronest.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andronest.R
import com.andronest.navigation.Navigation
import com.andronest.room.FavoriteStopEntity
import com.andronest.ui.theme.SearchTextColor
import com.andronest.viewmodels.FavoritesViewModel

@Composable
fun FavoritesItem(
    item: FavoriteStopEntity,
    navController: NavController,
    viewModel: FavoritesViewModel,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
        .fillMaxSize()) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.weight(0.2f)) {

            Row(modifier = modifier.fillMaxWidth()) {
                Icon(
                    modifier = modifier
                        .size(35.dp)
                        .clickable(onClick = { viewModel.deleteFavorite(item.id) }),
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Remove from favorites")
                Icon(
                    modifier = modifier
                        .size(35.dp)
                        .clickable(
                            onClick = { navController.navigate(Navigation.MapFavoriteScreen.createRoute(item))}
                        ),
                    painter = painterResource(R.drawable.map_search_24),
                    contentDescription = "Search on map")
            }
        }

        Spacer(Modifier.width(5.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = modifier.weight(1f)) {

            Text(
                color = SearchTextColor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = item.name
            )
        }
    }
}

