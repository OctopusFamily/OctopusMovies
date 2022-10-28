package com.octopus.moviesapp.ui.lists

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentListDetailsBinding
import com.octopus.moviesapp.domain.model.ListDetails
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListDetailsFragment() : BaseFragment<FragmentListDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_list_details
    override val viewModel: ListDetailsViewModel by viewModels()
    private lateinit var listDetailsAdapter: ListDetailsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeEvents()
    }

    private fun setAdapter() {
        listDetailsAdapter = ListDetailsAdapter(emptyList(), viewModel)
        binding.savedMovies.adapter = listDetailsAdapter
    }

    private fun observeEvents() {
        viewModel.itemId.observeEvent(viewLifecycleOwner) {
            navigateToMovieDetails(it)
        }
        viewModel.listDetails.observe(viewLifecycleOwner) {
            it.toData()?.let {
                checkIfEmptyList(it)
            }
        }

        viewModel.isArrowBackClicked.observeEvent(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }
    }

    private fun checkIfEmptyList(listDetails: List<ListDetails>) {
        if (listDetails.isEmpty()) {
            viewModel.setEmptyListAnimation(true)
        } else {
            viewModel.setEmptyListAnimation(false)
            listDetailsAdapter.setItems(listDetails)
        }
    }

    private fun navigateToMovieDetails(id: Int) {
        findNavController().navigate(
            ListDetailsFragmentDirections.actionListDetailsFragmentToMovieDetailsFragment(id)
        )
    }
}