package com.octopus.moviesapp.ui.person_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentPersonDetailsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.movie_details.MovieDetailsFragmentDirections
import com.octopus.moviesapp.ui.person_details.uistate.PersonDetailsUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonMovieUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonTVShowUiState
import com.octopus.moviesapp.util.RecyclerViewItem
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<FragmentPersonDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_person_details
    override val viewModel: PersonDetailsViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    private val itemsList = mutableListOf<RecyclerViewItem>()
    private lateinit var personDetailsAdapter: PersonDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personDetailsAdapter = PersonDetailsAdapter(itemsList, viewModel, viewModel)
        observePersonDetailsUiState()
        handleEvents()
        binding.recyclerPersonDetails.adapter = personDetailsAdapter
    }

    override fun onPause() {
        super.onPause()
        itemsList.clear()
    }

    private fun observePersonDetailsUiState() {
        lifecycleScope.launch {
            viewModel.personDetailsState.collectLatest {
                setPersonDetails(it.detailsUiState)
                setPersonMovies(it.moviesUiState)
                setPersonTVShows(it.TVShowUiState)
            }
        }
    }

    private fun setPersonDetails(personDetailsUiState: PersonDetailsUiState) {
        if (personDetailsUiState.biography.isNotEmpty()) {
            itemsList.add(0, RecyclerViewItem.PersonInfoDetailsItem(personDetailsUiState))
            personDetailsAdapter.setItems(itemsList)
        }
    }

    private fun setPersonMovies(personMoviesUiState: List<PersonMovieUiState>) {
        if (personMoviesUiState.isNotEmpty()) {
            itemsList.add(RecyclerViewItem.ImageMovieItem(personMoviesUiState))
            personDetailsAdapter.setItems(itemsList)
        }

    }

    private fun setPersonTVShows(personTvShowUiState: List<PersonTVShowUiState>) {
        if (personTvShowUiState.isNotEmpty()) {
            itemsList.add(RecyclerViewItem.ImageTvShowItem(personTvShowUiState))
            personDetailsAdapter.setItems(itemsList)
        }

    }

    private fun handleEvents() {
        viewModel.navigateBack.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.navigateToMovieDetails.observeEvent(viewLifecycleOwner) { movieId ->
            navigateToMovieDetails(movieId)
        }
        viewModel.navigateToTVShowDetails.observeEvent(viewLifecycleOwner) { tvShowId ->
            navigateToTVShowDetails(tvShowId)
        }
    }

    private fun navigateToMovieDetails(movieId: Int){
        requireView().findNavController().navigate(
            PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(movieId
            )
        )
    }

    private fun navigateToTVShowDetails(tvShowId: Int){
        requireView().findNavController().navigate(
            PersonDetailsFragmentDirections.actionPersonDetailsFragmentToTVShowDetailsFragment(
                tvShowId
            )
        )
    }



}