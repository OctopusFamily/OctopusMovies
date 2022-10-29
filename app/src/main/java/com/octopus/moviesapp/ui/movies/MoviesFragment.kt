package com.octopus.moviesapp.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMoviesBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.base.MyLoadStateAdapter
import com.octopus.moviesapp.util.extensions.createChip
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movies
    override val viewModel: MoviesViewModel by viewModels()

    private lateinit var moviesAdapter: MoviesPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setChips()
        initMoviesAdapter()
        observeMainState()
        handleEvents()
    }

    private fun initMoviesAdapter() {
        moviesAdapter = MoviesPagingAdapter(viewModel)
        val footerStateAdapter = MyLoadStateAdapter(moviesAdapter::retry)
        binding.moviesRecyclerView.adapter = moviesAdapter.withLoadStateFooter(footer = footerStateAdapter)
    }

    private fun observeMainState() {
        lifecycleScope.launch {
            viewModel.moviesMainUiState.collectLatest {
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
    }

    private fun setChips() {
        binding.moviesChipGroup.run {
            createChip(getString(R.string.popular), false)
            createChip(getString(R.string.now_playing), false)
            createChip(getString(R.string.upcoming), false)
            createChip(getString(R.string.top_rated), false)
        }
    }

    private fun navigateToMovieDetails(movieId: Int) {
        requireView().findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movieId))
    }
}