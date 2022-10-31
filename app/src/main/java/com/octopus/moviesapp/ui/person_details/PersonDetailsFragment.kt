package com.octopus.moviesapp.ui.person_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentPersonDetailsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
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
        handlePersonDetails()
        handlePersonMovie()
        handlePersonTvShow()
        handleEvents()
        binding.recyclerPersonDetails.adapter = personDetailsAdapter
    }

    private fun handlePersonDetails() {
        lifecycleScope.launch {
            viewModel.personDetailsState.collectLatest {

                itemsList.add(0, RecyclerViewItem.PersonInfoDetailsItem(it.personDetailsUiState))
                personDetailsAdapter.setItems(itemsList)

            }
        }

    }

    private fun handlePersonMovie() {

        lifecycleScope.launch {
            viewModel.personDetailsState.collectLatest {

                itemsList.add(RecyclerViewItem.ImageMovieItem(it.personMoviesUiState))
                personDetailsAdapter.setItems(itemsList)

            }
        }

    }

    private fun handlePersonTvShow() {
        lifecycleScope.launch {
            viewModel.personDetailsState.collectLatest {

                itemsList.add(RecyclerViewItem.ImageTvShowItem(it.personTvShowUiState))
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