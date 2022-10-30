package com.octopus.moviesapp.ui.movies_genre

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMoviesGenreBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.base.MyLoadStateAdapter
import com.octopus.moviesapp.ui.movies.MoviesPagingAdapter
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesGenreFragment : BaseFragment<FragmentMoviesGenreBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movies_genre
    override val viewModel: MoviesGenreViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    private lateinit var moviesAdapter: MoviesPagingAdapter

    private val args: MoviesGenreFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadMovies(args.genre.id, args.genre.name)
        initMoviesAdapter()
        observeMainState()
        handleEvents()
    }

    private fun initMoviesAdapter() {
        moviesAdapter = MoviesPagingAdapter(viewModel)
        val footerStateAdapter = MyLoadStateAdapter(moviesAdapter::retry)
        binding.moviesRecyclerView.adapter =
            moviesAdapter.withLoadStateFooter(footer = footerStateAdapter)
    }

    private fun observeMainState() {
        lifecycleScope.launch {
            viewModel.movieGenreState.collectLatest {
                it.moviesUiState.collectLatest { pagingData ->
                    moviesAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun handleEvents() {
        viewModel.navigateToMovieDetails.observeEvent(viewLifecycleOwner) { movieId ->
            navigateToMovieDetails(movieId)
        }
        viewModel.navigateBack.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    private fun navigateToMovieDetails(movieId: Int) {
        requireView().findNavController()
            .navigate(
                MoviesGenreFragmentDirections
                    .actionMoviesGenreFragmentToMovieDetailsFragment(movieId)
            )
    }
}
