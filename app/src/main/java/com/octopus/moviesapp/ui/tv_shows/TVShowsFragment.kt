package com.octopus.moviesapp.ui.tv_shows

import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentTvShowsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowsFragment : BaseFragment<FragmentTvShowsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tv_shows
    override val viewModel: TVShowsViewModel by viewModels()
}