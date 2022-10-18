package com.octopus.moviesapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentSearchBinding
import com.octopus.moviesapp.domain.types.SearchType
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.tv_shows.TVShowsFragmentDirections
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.searchResult.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success)
                binding.searchRecyclerView.adapter = SearchAdapter(state.data, viewModel)
        }


        viewModel.navigateToDetails.observeEvent(viewLifecycleOwner) { id ->
            if (viewModel.searchFilter.value  == SearchType.MOVIE.pathName)
                navigateToMovieDetails(id)
            else
                navigateToTVShowDetails(id)
        }



    }

    private fun navigateToTVShowDetails(tvShowId: Int) {
        requireView().findNavController()
            .navigate(SearchFragmentDirections
                .actionSearchFragmentToTVShowDetailsFragment(tvShowId))
    }

    private fun navigateToMovieDetails(movieId: Int) {
        requireView().findNavController()
            .navigate(SearchFragmentDirections
                .actionSearchFragmentToMovieDetailsFragment(movieId))
    }


}




