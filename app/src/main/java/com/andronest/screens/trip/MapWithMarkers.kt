package com.andronest.screens.trip

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapWithMarkers() {
    val stockholm = LatLng(59.3293, 18.0686)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(stockholm, 12f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = stockholm),
            title = "Stockholm Palace",
            snippet = "Official residence of the King"
        )

        // Draw a simple route
        val route = listOf(
            LatLng(59.3268, 18.0702),
            LatLng(59.3275, 18.0670),
            LatLng(59.3293, 18.0686)
        )

        Polyline(
            points = route,
            color = Color.Blue,
            width = 12f
        )
    }
}

