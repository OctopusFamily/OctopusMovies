package com.octopus.moviesapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.octopus.moviesapp.data.local.DataStorePref
import com.octopus.moviesapp.data.local.DataStorePreferences
import com.octopus.moviesapp.domain.types.Theme
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.SettingsService
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
@AndroidEntryPoint
class MyApplication @Inject constructor(private val dataStorePreferences: DataStorePref) : Application() {

    val settingsService = SettingsService
    override fun onCreate() {
        super.onCreate()
        setDefaultTheme()
    }

    private fun setDefaultTheme() {
        CoroutineScope(Dispatchers.Main).launch {
            dataStorePreferences.readString(Constants.DARK_MODE).collect { currentTheme ->
                if (currentTheme == null) {
                    val sysTheme = AppCompatDelegate.getDefaultNightMode()
                    if (sysTheme == AppCompatDelegate.MODE_NIGHT_NO){
                        dataStorePreferences.writeString(Constants.DARK_MODE, Theme.LIGHT.name)
                        settingsService.updateAppTheme(Theme.LIGHT)
                    }else if (sysTheme == AppCompatDelegate.MODE_NIGHT_YES)
                        dataStorePreferences.writeString(Constants.DARK_MODE, Theme.DARK.name)
                    settingsService.updateAppTheme(Theme.DARK)
                } else {

                }
            }
        }
    }
}