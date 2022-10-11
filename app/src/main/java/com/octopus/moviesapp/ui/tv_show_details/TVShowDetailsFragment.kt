package com.octopus.moviesapp.ui.tv_show_details

import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentTvShowDetailsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowDetailsFragment : BaseFragment<FragmentTvShowDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tv_show_details
    override val viewModel: TVShowDetailsViewModel by viewModels()
}