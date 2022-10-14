package com.octopus.moviesapp.ui.movie_details

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMovieDetailsBinding
import com.octopus.moviesapp.domain.sealed.RecyclerViewItem
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.trailer.TrailerActivity
import com.octopus.moviesapp.util.Constants.TRAILER_KEY
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.util.observeEvent
import com.octopus.moviesapp.util.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val itemsList = mutableListOf<RecyclerViewItem>()
    private lateinit var movieDetailsAdapter: MovieDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadMovieDetails(args.movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsAdapter = MovieDetailsAdapter(itemsList, viewModel as NestedGenresListener, viewModel as NestedCastListener)
        handleMovieDetails()
        handleMovieCast()
        handleEvents()
        observeTrailerState()
    }

    private fun handleEvents() {
        viewModel.rateMovie.observeEvent(viewLifecycleOwner) {
            requireContext().showShortToast(getString(R.string.coming_soon))
        }
        viewModel.saveToWatchList.observeEvent(viewLifecycleOwner) {
            requireContext().showShortToast(getString(R.string.coming_soon))
        }
        viewModel.playTrailer.observeEvent(viewLifecycleOwner) { trailerKey ->
            sendToTrailerActivity(trailerKey)
        }
    }

    private fun sendToTrailerActivity(trailerKey: String) {
        Intent(requireContext(), TrailerActivity::class.java).run {
            putExtra(TRAILER_KEY, trailerKey)
            startActivity(this)
        }
        viewModel.navigateBack.observeEvent(viewLifecycleOwner) {
             findNavController().popBackStack()
        }
        viewModel.playTrailer.observeEvent(viewLifecycleOwner) {
            // Replace this with intent!
        }
    }

    private fun handleMovieDetails() {
        viewModel.movieDetailsState.observe(viewLifecycleOwner) { uiState ->
            if (uiState is UiState.Success) {
                viewModel.onLoadMovieDetailsSuccess(uiState.data)
                itemsList.add(0, RecyclerViewItem.MovieInfoItem(uiState.data))
                movieDetailsAdapter.setItems(itemsList)
                binding.movieDetailsRecyclerView.adapter = movieDetailsAdapter
            }
        }
    }

    private fun handleMovieCast() {
        viewModel.movieCastState.observe(viewLifecycleOwner) { uiState ->
            if (uiState is UiState.Success) {
                itemsList.add(RecyclerViewItem.CastItem(uiState.data))
                movieDetailsAdapter.setItems(itemsList)
            }
        }
    }

    private fun observeTrailerState() {
        viewModel.movieTrailerState.observe(viewLifecycleOwner) { uiState ->
            if (uiState is UiState.Success) {
                viewModel.onLoadTrailerSuccess(uiState.data)
            }
        }
    }
}