package com.octopus.moviesapp.ui.movies_genre

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMoviesGenreBinding
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesGenreFragment : BaseFragment<FragmentMoviesGenreBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movies_genre
    override val viewModel: MoviesGenreViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    private val args: MoviesGenreFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadMovies(args.genre.id, args.genre.name)
        handleEvents()

    }

    private fun handleEvents() {
        viewModel.movieGenreState.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success)
                binding.moviesRecyclerView.adapter = MoviesGenreAdapter(state.data, viewModel)
        }
        viewModel.navigateToMovieDetails.observeEvent(viewLifecycleOwner) { movieId ->
            navigateToMovieDetails(movieId)
        }
        viewModel.navigateBack.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

    }

    private fun navigateToMovieDetails(movieId: Int) {
        requireView().findNavController()
            .navigate(MoviesGenreFragmentDirections
                .actionMoviesGenreFragmentToMovieDetailsFragment(movieId)
            )
    }

}