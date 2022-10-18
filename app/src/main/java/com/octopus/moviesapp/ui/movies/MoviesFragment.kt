package com.octopus.moviesapp.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMoviesBinding
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.base.BasePagingAdapter
import com.octopus.moviesapp.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movies
    override val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMoviesAdapter()
        handleEvents()
    }

    private fun initMoviesAdapter() {
        val moviesAdapter = MoviesAdapter(viewModel)
        binding.moviesRecyclerView.adapter = moviesAdapter
        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                moviesAdapter.submitData(it)
            }
        }
    }

    private fun handleEvents() {
        viewModel.navigateToMovieDetails.observeEvent(viewLifecycleOwner) { movieId ->
            navigateToMovieDetails(movieId)
        }
    }

    private fun navigateToMovieDetails(movieId: Int) {
        requireView().findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movieId))
    }
}