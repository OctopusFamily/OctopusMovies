package com.octopus.moviesapp.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.movie_details.uistate.MovieDetailsUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState
import com.octopus.moviesapp.util.RecyclerViewItem
import com.octopus.moviesapp.util.extensions.navigateToTrailerActivity
import com.octopus.moviesapp.util.extensions.observeEvent
import com.octopus.moviesapp.util.extensions.showShortToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<com.octopus.moviesapp.databinding.FragmentMovieDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()
    private val args : MovieDetailsFragmentArgs by navArgs()
    override var bottomNavigationViewVisibility = View.GONE

    private val itemsList = mutableListOf<RecyclerViewItem>()
    private lateinit var movieDetailsAdapter: MovieDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsAdapter = MovieDetailsAdapter(itemsList, viewModel, viewModel)
        observeMovieDetailsUiState()
        binding.movieDetailsRecyclerView.adapter = movieDetailsAdapter
        handleEvents()
    }

    override fun onPause() {
        super.onPause()
        itemsList.clear()
    }

    private fun observeMovieDetailsUiState() {
        lifecycleScope.launch {
            viewModel.movieDetailsState.collectLatest {
                setMovieDetails(it.info)
                setMovieCast(it.cast)
            }
        }
    }

    private fun setMovieDetails(movieDetails: MovieDetailsUiState) {
        if (movieDetails.id != 0) {
        itemsList.add(0, RecyclerViewItem.MovieInfoItem(movieDetails))
        movieDetailsAdapter.setItems(itemsList)
        }

    }

    private fun setMovieCast(castUiState: List<CastUiState> ) {
        if (castUiState.isNotEmpty()) {
            itemsList.add(RecyclerViewItem.CastItem(castUiState))
            movieDetailsAdapter.setItems(itemsList)
        }
    }

    private fun navigateToMoviesGenreFragment(genre: Genre) {
        requireView().findNavController()
            .navigate(
                MovieDetailsFragmentDirections.actionMovieDetailsFragmentToMoviesGenreFragment(
                    genre.id,
                    genre.name,
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
        viewModel.isSaveIconClicked.observeEvent(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailsFragmentToBottomSheetSaveTo(args.movieId)
                )
            }
        }
    }

}
