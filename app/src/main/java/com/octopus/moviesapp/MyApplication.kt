package com.octopus.moviesapp

import android.app.Application
import com.octopus.moviesapp.data.local.datastore.DataStorePreferences
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
    lateinit var dataStorePreferences: DataStorePreferences

    override fun onCreate() {
        super.onCreate()
        setTheme()
        checkFirstTimeLaunch()
    }

    private fun checkFirstTimeLaunch() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStorePreferences.readString(Constants.SESSION_ID_KEY).let{ id ->
                isFirstTimeLaunch = id == null
                id?.let {
                    sessionId = it
                }
            }
        }
    }

    private fun setTheme() {
        CoroutineScope(Dispatchers.Unconfined).launch {
            dataStorePreferences.readString(Constants.THEME_KEY).let { currentTheme ->
                currentTheme?.let {
                    chosenAppTheme = Theme.valueOf(it)
                }
            }
        }
    }

    companion object {
        var isFirstTimeLaunch = false
        var chosenAppTheme: Theme? = null
        var sessionId = Constants.EMPTY_TEXT
    }
}