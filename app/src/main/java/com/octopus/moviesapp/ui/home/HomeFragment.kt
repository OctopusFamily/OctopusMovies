package com.octopus.moviesapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentHomeBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.ConnectionTracker
import com.octopus.moviesapp.util.ConnectionTrackerImpl
import com.octopus.moviesapp.util.SettingsService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var connectionTracker: ConnectionTracker

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val settingsService = SettingsService

        lifecycleScope.launch {
            Log.d("MALT", "IS: ${connectionTracker.isInternetConnectionAvailable()}")
        }
    }
}