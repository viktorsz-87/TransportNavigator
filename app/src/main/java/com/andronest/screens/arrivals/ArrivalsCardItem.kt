package com.andronest.screens.arrivals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andronest.R
import com.andronest.model.ArrivalsResponse
import com.andronest.ui.theme.SearchTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArrivalsCardItem(
    item: ArrivalsResponse.Arrival,
    modifier: Modifier = Modifier) {

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxSize()){

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier=modifier.fillMaxSize().weight(0.1f)){

            Icon(
                modifier=modifier.size(30.dp),
                imageVector = Icons.Filled.Place, contentDescription = "Stop Type")
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier=modifier.fillMaxSize().weight(1f)){

            Text(
                color = SearchTextColor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                text = "${item.name}")
        }
    }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxSize()){

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier=modifier.fillMaxSize()){

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxSize()){

                if(item.productAtStop.catOut.contains("BLT")){
                    Icon(modifier=modifier.size(35.dp),
                        painter = painterResource(R.drawable.bus_24),
                        contentDescription = "Bus")
                } else if(item.productAtStop.catOut.contains("ULT")){
                    Icon(modifier=modifier.size(35.dp),
                        painter = painterResource(R.drawable.subway_24),
                        contentDescription = "Subway")
                }

                Text(
                    color = SearchTextColor,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = "${item.productAtStop.displayNumber} to ${item.origin}")
            }
        }
    }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxSize()){

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier=modifier.fillMaxSize().padding(start = 5.dp)){

            Text(
                color = SearchTextColor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                text = "${item.time.take(5)} ")
        }
    }
}