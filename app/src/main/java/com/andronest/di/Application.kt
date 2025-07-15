package com.andronest.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class TransportNavigatorApp : Application(){

    companion object {
        var userLatitude: Double = 0.0
        var userLongitude: Double = 0.0
        var userStopId = ""
    }
}