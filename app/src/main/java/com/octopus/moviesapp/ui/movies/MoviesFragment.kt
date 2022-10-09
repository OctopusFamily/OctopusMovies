package com.octopus.moviesapp.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMoviesBinding
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
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
        viewModel.stateOfListMovies.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success) {
                    binding.moviesRecyclerView.adapter = MoviesAdapter(state.data , viewModel)
                }
            }
        }


}