package com.octopus.moviesapp.ui.movie_details

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMovieDetailsBinding
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.trailer.TrailerActivity
import com.octopus.moviesapp.util.observeEvent
import com.octopus.moviesapp.util.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        viewModel.loadMovieDetails(args.movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.movieDetailsState.observe(viewLifecycleOwner) { uiState ->
            if (uiState is UiState.Success) {
                viewModel.onLoadMovieDetailsSuccess(uiState.data)
            }
        }
        viewModel.rateMovie.observeEvent(viewLifecycleOwner) {
            requireContext().showShortToast(getString(R.string.coming_soon))
        }
        viewModel.saveToWatchList.observeEvent(viewLifecycleOwner) {
            requireContext().showShortToast(getString(R.string.coming_soon))
        }
        viewModel.playTrailer.observeEvent(viewLifecycleOwner) {
            val intent = Intent(requireContext(), TrailerActivity::class.java)
            intent.putExtra("trailersKey", it)
            startActivity(intent)
        }
    }
}