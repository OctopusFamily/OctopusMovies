package com.octopus.moviesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        lateinit var application: Application private set
    }
    override fun onCreate() {
        super.onCreate()
        application = this
    }
}