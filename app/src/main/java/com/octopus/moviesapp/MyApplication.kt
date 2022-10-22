package com.octopus.moviesapp

import android.app.Application
import com.octopus.moviesapp.data.local.datastore.DataStorePref
import com.octopus.moviesapp.domain.types.Theme
import com.octopus.moviesapp.util.Constants
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {
    @Inject
    lateinit var dataStorePreferences: DataStorePref

    override fun onCreate() {
        super.onCreate()
        setTheme()
        checkFirstTimeLaunch()
    }

    private fun checkFirstTimeLaunch() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStorePreferences.readString(Constants.SESSION_ID_KEY).collect { sessionId ->
                isFirstTimeLaunch = sessionId == null
            }
        }
    }

    private fun setTheme() {
        CoroutineScope(Dispatchers.Unconfined).launch {
            dataStorePreferences.readString(Constants.DARK_MODE).collect { currentTheme ->
                currentTheme?.let {
                    chosenAppTheme = Theme.valueOf(it)
                }
            }
        }
    }

    companion object {
        var isFirstTimeLaunch = false
        var chosenAppTheme: Theme? = null
    }
}