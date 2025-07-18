package com.andronest.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    currentDest: String?,
    onSearch: ()->Unit = {},
    onTrip: ()->Unit = {},
    onFavorites: ()->Unit = {},
    onArrivals: ()->Unit = {},
    modifier: Modifier = Modifier
) {

    val navBarItems = listOf(
        BottomNavBarItem(
            icon = Icons.Default.Search,
            title = "Nearby Stops",
            route = Navigation.SearchScreen.getBaseRoute(),
            description = "Search stops.."
        ),
        BottomNavBarItem(
            icon = Icons.Default.Info,
            title = "Search Arrivals",
            route = Navigation.ArrivalsScreen.getBaseRoute(),
            description = "Arrivals.."
        ),
        BottomNavBarItem(
            icon = Icons.Default.LocationOn,
            title = "Search Trip",
            route = Navigation.TripScreen.getBaseRoute(),
            description = "Trip.."
        ),
        BottomNavBarItem(
            icon = Icons.Default.Favorite,
            title = "Favorites",
            route = Navigation.FavoritesScreen.getBaseRoute(),
            description = "Favorites.."
        )
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer

    ) {

        navBarItems.forEachIndexed { index, item ->

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIndicatorColor = Color(0xC4409DDA)
                ),
                label = {
                    Text(
                    fontWeight = FontWeight.Bold,
                    text = item.title,
                    style = MaterialTheme.typography.labelMedium)
                },
                selected = currentDest == item.route,
                onClick = {
                    when(item.route){
                        Navigation.SearchScreen.getBaseRoute() -> onSearch()
                        Navigation.FavoritesScreen.getBaseRoute() -> onFavorites()
                        Navigation.ArrivalsScreen.getBaseRoute() -> onArrivals()
                        Navigation.TripScreen.getBaseRoute() -> onTrip()
                    }
                },
                icon = {
                    Icon(
                        modifier = modifier.size(40.dp),
                        imageVector = item.icon,
                        contentDescription = item.description
                    )
                }
            )
        }
    }
}

data class BottomNavBarItem(
    val icon: ImageVector,
    val title: String,
    val route: String,
    val description: String
)