package com.octopus.moviesapp.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.RecyclerViewItem
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.navigateToTrailerActivity
import com.octopus.moviesapp.util.extensions.observeEvent
import com.octopus.moviesapp.util.extensions.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<com.octopus.moviesapp.databinding.FragmentMovieDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE


    private val itemsList = mutableListOf<RecyclerViewItem>()
    private lateinit var movieDetailsAdapter: MovieDetailsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsAdapter = MovieDetailsAdapter(
            itemsList,
            viewModel,
            viewModel
        )
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
        viewModel.navigateBack.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.playTrailer.observeEvent(viewLifecycleOwner) { trailerKey ->
            if (trailerKey.isNotEmpty()) {
                requireContext().navigateToTrailerActivity(trailerKey)
            } else {
                requireContext().showShortToast(getString(R.string.no_source_available))
            }
        }
        viewModel.navigateToMoviesGenre.observeEvent(viewLifecycleOwner) { genre ->
            navigateToMoviesGenreFragment(genre)
        }
        viewModel.navigateToPersonDetails.observeEvent(viewLifecycleOwner) { idPerson ->
            navigateToPersonDetails(idPerson)
        }

        viewModel.navigateToPersonDetails.observeEvent(viewLifecycleOwner) { castId ->
            navigateToPersonDetailsFragment(castId)
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
               // itemsList.add(RecyclerViewItem.CastItem(uiState.data))
               // movieDetailsAdapter.setItems(itemsList)
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

    private fun navigateToMoviesGenreFragment(genre: Genre) {
        requireView().findNavController()
            .navigate(
                MovieDetailsFragmentDirections.actionMovieDetailsFragmentToMoviesGenreFragment(
                    genre
                )
            )
    }

    private fun navigateToPersonDetails(personId: Int) {
        requireView().findNavController().navigate(
            MovieDetailsFragmentDirections.actionMovieDetailsFragmentToPersonDetailsFragment(
                personId
            )
        )
    }

    private fun navigateToPersonDetailsFragment(castId: Int) {
        requireView().findNavController()
            .navigate(
                MovieDetailsFragmentDirections.actionMovieDetailsFragmentToPersonDetailsFragment(
                    castId
                )
            )
    }
}