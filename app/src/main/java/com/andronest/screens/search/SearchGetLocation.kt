package com.andronest.screens.search

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.andronest.di.TransportNavigatorApp
import com.andronest.viewmodels.SearchViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

@Composable
fun GetLocation(
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var locationText by remember { mutableStateOf("Getting location...") }

    // Create location request with 10s interval
    val locationRequest = remember {
        LocationRequest.Builder(10000).apply {
            setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            setMinUpdateIntervalMillis(5000)
        }.build()
    }

    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) fetchLocation(context,locationClient, locationRequest) { lat, lon ->
            locationText = "Location: $lat, $lon"
            viewModel.getNearbyStops(lat = lat.toString(), long = lon.toString())


            TransportNavigatorApp.userLatitude = lat
            TransportNavigatorApp.userLongitude = lon
            viewModel.getUserStopId()
        }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fetchLocation(context,locationClient, locationRequest) { lat, lon ->
                locationText = "Location: $lat, $lon"
                viewModel.getNearbyStops(lat = lat.toString(), long = lon.toString())

                //TODO förbättra senare
                TransportNavigatorApp.userLatitude = lat
                TransportNavigatorApp.userLongitude = lon
                viewModel.getUserStopId()
            }
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    Text(locationText)
}


@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
fun fetchLocation(
    context: Context,
    client: FusedLocationProviderClient,
    request: LocationRequest,
    callback: (lat: Double, lon: Double) -> Unit) {

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            result.lastLocation?.let {
                callback(it.latitude, it.longitude)
            }
            client.removeLocationUpdates(this) // Important cleanup
        }
    }

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        client.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )
    }

}