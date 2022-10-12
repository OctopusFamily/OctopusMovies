package com.octopus.moviesapp.ui.main

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.ActivityMainBinding
import com.octopus.moviesapp.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.main_fragment_container_view)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}