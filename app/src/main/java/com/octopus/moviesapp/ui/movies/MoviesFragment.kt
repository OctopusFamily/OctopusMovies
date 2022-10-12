package com.octopus.moviesapp.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMoviesBinding
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movies
    override val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }


    private fun handleEvents() {
        viewModel.moviesListState.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success) {
                binding.moviesRecyclerView.adapter = MoviesAdapter(state.data, viewModel)
            }
        }

        navigateToMovieDetails()
    }

    private fun navigateToMovieDetails() {
        viewModel.navigateToMoviesDetails.observeEvent(viewLifecycleOwner) {
            if(it != null)
            requireView().findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(it))
        }
    }


}