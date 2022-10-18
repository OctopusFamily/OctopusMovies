package com.octopus.moviesapp.ui.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentSearchBinding
import com.octopus.moviesapp.domain.types.SearchType
import com.octopus.moviesapp.ui.base.BaseFragment
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
        viewModel.filteredSearchResults.observe(viewLifecycleOwner) { searchResults ->
                binding.searchRecyclerView.adapter = SearchAdapter(searchResults, viewModel)
        }


        viewModel.navigateToDetails.observeEvent(viewLifecycleOwner) { id ->
            when (viewModel.searchType.value) {
                SearchType.MOVIE.pathName -> navigateToMovieDetails(id)
                SearchType.TV.pathName -> navigateToTVShowDetails(id)
                SearchType.PERSON.pathName -> navigateToPersonDetails(id)
            }

        }

    }

    private fun navigateToTVShowDetails(tvShowId: Int) {
        findNavController().navigate(
            SearchFragmentDirections
                .actionSearchFragmentToTVShowDetailsFragment(tvShowId)
        )
    }

    private fun navigateToMovieDetails(movieId: Int) {
        findNavController().navigate(
            SearchFragmentDirections
                .actionSearchFragmentToMovieDetailsFragment(movieId)
        )
    }

    private fun navigateToPersonDetails(personId: Int) {
        Toast.makeText(
            this.context,
            "the id of the clicked person is $personId",
            Toast.LENGTH_SHORT
        ).show()
        //TODO : not yet implement
    }


}




