package com.andronest

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andronest.navigation.Navigation
import com.andronest.screens.arrivals.ArrivalsScreen
import com.andronest.screens.search.SearchScreen
import com.andronest.ui.theme.TransportNavigatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TransportNavigatorTheme {
                val navController = rememberNavController()
                val backStackState by navController.currentBackStackEntryAsState()
                val currentRoute = backStackState?.destination?.route

                /*val currentRoute = navController.currentBackStackEntry?.let {
                    derivedStateOf {
                        it.destination.route
                    }
                }?.value*/

                NavHost(
                    navController = navController,
                    startDestination = Navigation.SearchScreen.route
                ) {

                    composable(route = Navigation.SearchScreen.route) {
                        SearchScreen(
                            currentDest = currentRoute,
                            navController = navController)
                    }
                    composable(
                        arguments = listOf(
                            navArgument(name = "id"){
                                type= NavType.StringType
                                nullable=true
                            }
                        ),
                        route = Navigation.ArrivalsScreen.route) {

                        val id = it.arguments?.getString("id")
                        id?.let {
                            ArrivalsScreen(currentDest = currentRoute, id=id)
                        }

                    }
                }
            }
        }
    }
}
