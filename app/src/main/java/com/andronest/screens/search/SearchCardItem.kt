package com.andronest.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andronest.model.NearbyStopsResponse
import com.andronest.ui.theme.SearchTextColor

@Composable
fun SearhCardItem(
    stopLocation: NearbyStopsResponse.StopLocationOrCoordLocation.StopLocation,
    modifier: Modifier = Modifier) {

    stopLocation.productAtStop?.forEach { type->

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxSize()){

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier=modifier.weight(0.12f).fillMaxSize()){

                when{
                    type.getProductType()=="Bus"-> {
                        Icon(
                            modifier=modifier.size(35.dp),
                            imageVector = Icons.Default.Favorite, contentDescription = "Stop Type")
                    }
                    type.getProductType()=="Metro"-> {
                        Icon(
                            modifier=modifier.size(35.dp),
                            imageVector = Icons.Default.Home, contentDescription = "Stop Type")
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier=modifier.weight(1f).fillMaxSize()){

                Text(
                    color = SearchTextColor,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = "${stopLocation.name}, ${stopLocation.dist}m")
            }
        }
    }
}