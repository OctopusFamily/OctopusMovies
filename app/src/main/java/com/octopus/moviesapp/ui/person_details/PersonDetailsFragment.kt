package com.octopus.moviesapp.ui.person_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentPersonDetailsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.RecyclerViewItem
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<FragmentPersonDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_person_details
    override val viewModel: PersonDetailsViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    private val args: PersonDetailsFragmentArgs by navArgs()

    private val itemsList = mutableListOf<RecyclerViewItem>()
    private lateinit var personDetailsAdapter: PersonDetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadPersonDetailsData(args.personId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personDetailsAdapter = PersonDetailsAdapter(itemsList, viewModel, viewModel)
        handlePersonDetails()
        handlePersonMovie()
        handlePersonTvShow()
        handleEvents()
    }

    private fun handlePersonDetails() {
        viewModel.personDetailsState.observe(viewLifecycleOwner) { uiState ->
            if (uiState is UiState.Success) {
                viewModel.onLoadPersonDetailSuccess(uiState.data)
            }
        }
    }

    private fun handlePersonMovie() {
        viewModel.personMoviesState.observe(viewLifecycleOwner) { uiState ->
            if (uiState is UiState.Success) {
                viewModel.onLoadPersonMoviesSuccess(uiState.data)
                itemsList.add(0, RecyclerViewItem.ImageMovieItem(uiState.data))
                personDetailsAdapter.setItems(itemsList)
                binding.recyclerPersonDetails.adapter = personDetailsAdapter
            }
        }
    }

    private fun handlePersonTvShow() {
        viewModel.personTvShowState.observe(viewLifecycleOwner) { uiState ->
            if (uiState is UiState.Success) {
                viewModel.onLoadPersonTvShowSuccess(uiState.data)
                itemsList.add(0, RecyclerViewItem.ImageTvShowItem(uiState.data))
                personDetailsAdapter.setItems(itemsList)
            }
        }
    }

    private fun handleEvents() {
        viewModel.navigateBack.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

}