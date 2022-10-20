package com.octopus.moviesapp.ui.tv_shows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentTvShowsBinding
import com.octopus.moviesapp.domain.types.TVShowsCategory
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowsFragment : BaseFragment<FragmentTvShowsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tv_shows
    override val viewModel: TVShowsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onChipClick(TVShowsCategory.POPULAR)
    }

    private fun handleEvents() {
        viewModel.tvShowsListState.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success) {
                binding.tvShowsRecyclerView.adapter = TVShowsAdapter(state.data, viewModel)
            }
        }
        viewModel.navigateToTVShowDetails.observeEvent(viewLifecycleOwner) { tvShowId ->
            navigateToTVShowDetails(tvShowId)
        }
    }

    private fun navigateToTVShowDetails(tvShowId: Int) {
        requireView().findNavController().navigate(TVShowsFragmentDirections.actionTVShowsFragmentToTVShowDetailsFragment(tvShowId))

    }
}