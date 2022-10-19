package com.octopus.moviesapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.data.local.DataStorePref
import com.octopus.moviesapp.databinding.ActivityMainBinding
import com.octopus.moviesapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var dataStorePref: DataStorePref
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setStartDestination()
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

    private fun setStartDestination() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container_view) as NavHostFragment
        val graph = navHostFragment.navController.navInflater.inflate(R.navigation.main_navigation)

        CoroutineScope(Dispatchers.Main).launch {
            dataStorePref.readString(Constants.SESSION_ID_KEY).collect {
                if (it == null) {
                    graph.setStartDestination(R.id.loginFragment)
                } else {
                    graph.setStartDestination(R.id.homeFragment)
                }
                navHostFragment.navController.setGraph(graph, intent.extras)
            }
        }
    }
}