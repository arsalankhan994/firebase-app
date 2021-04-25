package com.app.mobileapptask

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Scopic : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}