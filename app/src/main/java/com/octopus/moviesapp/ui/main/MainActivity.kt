package com.octopus.moviesapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.data.local.DataStorePref
import com.octopus.moviesapp.databinding.ActivityMainBinding
import com.octopus.moviesapp.domain.types.Theme
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.SettingsService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var dataStorePreferences: DataStorePref
    private val settingsService = SettingsService

    init {
        setTheme()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.main_fragment_container_view)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun setBottomNavigationVisibility(visibility: Int) {
        binding.bottomNavigationView.visibility = visibility
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setTheme() {
        CoroutineScope(Dispatchers.Main).launch {
            dataStorePreferences.readString(Constants.DARK_MODE).collect { currentTheme ->
                if (currentTheme != null) {
                    settingsService.updateAppTheme(Theme.valueOf(currentTheme))
                }
            }
        }
    }
}