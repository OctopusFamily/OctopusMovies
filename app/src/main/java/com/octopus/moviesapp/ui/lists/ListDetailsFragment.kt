package com.octopus.moviesapp.ui.lists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentListDetailsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListDetailsFragment() : BaseFragment<FragmentListDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_list_details
    override val viewModel: ListDetailsViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE
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
        viewModel.item.observeEvent(viewLifecycleOwner) {
            navigateToMovieDetails(it.id)
        }

        viewModel.isArrowBackClicked.observeEvent(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }
    }

    private fun navigateToMovieDetails(id: Int) {
        findNavController().navigate(
            ListDetailsFragmentDirections.actionListDetailsFragmentToMovieDetailsFragment(id)
        )
    }
}