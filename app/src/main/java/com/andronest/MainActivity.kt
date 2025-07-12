package com.andronest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.derivedStateOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andronest.navigation.Navigation
import com.andronest.screens.SearchScreen
import com.andronest.ui.theme.TransportNavigatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TransportNavigatorTheme {
                val navController = rememberNavController()
                val currentRoute = navController.currentBackStackEntry?.let {
                    derivedStateOf {
                        it.destination.route
                    }
                }?.value

                NavHost(
                    navController = navController,
                    startDestination = Navigation.SearchScreen.route
                ){

                    composable(route = Navigation.SearchScreen.route) {
                        SearchScreen(currentRoute)
                    }
                }
            }
        }
    }
}
