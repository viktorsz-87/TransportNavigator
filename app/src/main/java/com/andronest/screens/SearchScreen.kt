package com.andronest.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andronest.R
import com.andronest.model.StopLocationWrapper
import com.andronest.navigation.BottomNavigationBar
import com.andronest.viewmodels.SearchViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    currentDest: String?,
    viewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val uiState by viewModel.searchUiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.errorChannel.collect { errorMessage ->
            snackbarHostState.showSnackbar(errorMessage)
        }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {Text("Nearby stops")}
            )
        },
        bottomBar = {
            BottomNavigationBar(currentDest, onClick = {})
        }

    ) { paddingValues ->

        GetLocation(viewModel)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // Remember(uiState): Only when reference changes..
            remember(uiState) {
                derivedStateOf {
                    // check the state values..
                    !uiState.isSearching &&
                    uiState.errorMessage == null &&
                    !uiState.results.isNullOrEmpty()
                }
            }.value.let { showResults->

                when(showResults){
                    true -> SearchResults(uiState.results)
                    else->  when {
                        uiState.isSearching -> SearchingState()
                        uiState.errorMessage != null -> ErrorState(uiState.errorMessage)
                        else -> EmptyState()
                    }
                }
            }
        }
    }
}

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

@Composable
fun EmptyState(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.LocationOn, contentDescription = null)
        Text(text = "No stops found", style = MaterialTheme.typography.titleMedium)
        Text(text = "Try moving to a different location")
    }
}

@Composable
fun SearchingState(modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun SearchResults(
    results: List<StopLocationWrapper>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {

        items(items = results) { stopLocation ->

            Text(text = "location: ${stopLocation.stopLocation?.name}")

            val prods = stopLocation.stopLocation?.productAtStop?.forEach {
                Text(text = "type: ${it.getProductType()}")
            }

        }
    }
}

@Composable
fun ErrorState(
    errorMessage: String?,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Text(
            text = stringResource(R.string.error, errorMessage.toString()),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}