package com.octopus.moviesapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentSearchBinding
import com.octopus.moviesapp.domain.types.SearchType
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
        handleObserve()
        setAdapter()
    }

    private fun handleObserve(){
        viewModel.searchQuery.observe(viewLifecycleOwner) {
            viewModel.getSearchMultiMedia(it)
        }

        viewModel.filteredSearchResults.observe(viewLifecycleOwner) { searchResults ->
            searchAdapter.setItems(searchResults)
        }
    }

    private fun setAdapter(){
        searchAdapter = SearchAdapter(emptyList(), viewModel)
        binding.searchRecyclerView.adapter = searchAdapter
    }

    private fun handleEvents() {
        viewModel.navigateToDetails.observeEvent(viewLifecycleOwner) { id ->
            when (viewModel.searchType) {
                SearchType.MOVIE -> navigateToMovieDetails(id)
                SearchType.TV -> navigateToTVShowDetails(id)
                SearchType.PERSON -> navigateToPersonDetails(id)
            }
        }

        viewModel.navigateBack.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

    }

    private fun navigateToTVShowDetails(tvShowId: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToTVShowDetailsFragment(tvShowId)
        )
    }

    private fun navigateToMovieDetails(movieId: Int) {
        findNavController().navigate(
            SearchFragmentDirections
                .actionSearchFragmentToMovieDetailsFragment(movieId)
        )
    }

    private fun navigateToPersonDetails(personId: Int) {
        findNavController().navigate(
            SearchFragmentDirections
                .actionSearchFragmentToPersonDetailsFragment(personId)
        )
    }


}




