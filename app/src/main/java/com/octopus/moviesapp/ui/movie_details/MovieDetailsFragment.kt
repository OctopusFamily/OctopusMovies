package com.octopus.moviesapp.ui.movie_details

import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMovieDetailsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()
}