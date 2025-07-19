package com.andronest.screens.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.andronest.model.response.TripResponse
import com.andronest.navigation.BottomNavigationBar
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    trip: TripResponse.Trip,
    currentDest: String?,
    navController: NavController
) {
    val snackbarHostState = SnackbarHostState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back")
                    }
                },
                title = { Text("Map") }
            )
        },
        bottomBar = {
            BottomNavigationBar(currentDest)
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {

            val stockholm = LatLng(59.3293, 18.0686)
            val cameraPositionState = rememberCameraPositionState {
               position = CameraPosition.fromLatLngZoom(stockholm, 9f)
            }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {

                trip.legList.leg.forEachIndexed { legIndex, leg ->

                    val polys: List<LatLng> = leg.stops?.stop?.map { stop ->
                        LatLng(stop.lat, stop.lon)
                    } ?: emptyList()


                    leg.stops?.stop?.let { stops ->

                        stops.forEachIndexed { stopIndex, stop ->

                            Marker(
                                state = MarkerState(position = LatLng(stop.lat, stop.lon)),
                                title = stop.name,
                                snippet = "Arriving: ${stop.arrTime ?: "No data"}"
                            )
                        }
                    }
                    Polyline(points = polys, color = Color.Blue, width = 12f)
                }
            }
        }
    }
}

