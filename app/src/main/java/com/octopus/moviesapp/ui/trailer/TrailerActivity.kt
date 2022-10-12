package com.octopus.moviesapp.ui.trailer

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.ActivityTrailerBinding
import com.octopus.moviesapp.ui.base.BaseActivity
import com.octopus.moviesapp.ui.main.MainViewModel

class TrailerActivity : BaseActivity<ActivityTrailerBinding>() {
    override val viewModel: TrailerViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.activity_trailer


}