package com.andronest.screens.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.andronest.R

@Composable
fun ErrorState(
    errorMessage: String?,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Icon(Icons.Default.LocationOn, contentDescription = null)
        Text(text = "No data found...", style = MaterialTheme.typography.titleMedium)
        Text(text = "Try again or try another stop")
    }
    Text(
        text = stringResource(R.string.error, errorMessage.toString()),
        color = Color.White,
    )
}
