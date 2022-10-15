package com.octopus.moviesapp.ui.tv_show_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentTvShowDetailsBinding
import com.octopus.moviesapp.util.RecyclerViewItem
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.ui.nested.NestedSeasonsListener
import com.octopus.moviesapp.util.navigateToTrailerActivity
import com.octopus.moviesapp.util.observeEvent
import com.octopus.moviesapp.util.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowDetailsFragment : BaseFragment<FragmentTvShowDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tv_show_details
    override val viewModel: TVShowDetailsViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    private val args: TVShowDetailsFragmentArgs by navArgs()

    private val itemsList = mutableListOf<RecyclerViewItem>()
    private lateinit var tvShowDetailsAdapter: TVShowDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadTVShowDetails(args.tvShowId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowDetailsAdapter = TVShowDetailsAdapter(
            itemsList,
            viewModel as NestedGenresListener,
            viewModel as NestedCastListener,
            viewModel as NestedSeasonsListener,
        )
        handleTVShowDetails()
        handleTVShowCast()
        handleEvents()
        observeTrailerState()
    }

    private fun handleEvents() {
        viewModel.rateTvShow.observeEvent(viewLifecycleOwner) {
            requireContext().showShortToast(getString(R.string.coming_soon))
        }
        viewModel.saveToWatchList.observeEvent(viewLifecycleOwner) {
            requireContext().showShortToast(getString(R.string.coming_soon))
        }
        viewModel.navigateBack.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.playTrailer.observeEvent(viewLifecycleOwner) { trailerKey ->
            if (trailerKey.isNotEmpty()) {
                requireContext().navigateToTrailerActivity(trailerKey)
            } else {
                requireContext().showShortToast(getString(R.string.no_source_available))
            }
        }
    }

    private fun handleTVShowDetails() {
        viewModel.tvShowDetailsState.observe(viewLifecycleOwner) { uiState ->
            if (uiState is UiState.Success) {
                viewModel.onLoadTVShowDetailsSuccess(uiState.data)
                itemsList.add(0, RecyclerViewItem.TVShowInfoItem(uiState.data))
                itemsList.add(1, RecyclerViewItem.SeasonItem(uiState.data.seasons))
                tvShowDetailsAdapter.setItems(itemsList)
                binding.tvShowDetailsRecyclerView.adapter = tvShowDetailsAdapter
            }
        }
    }

    private fun observeTrailerState() {
        viewModel.tvTrailerState.observe(viewLifecycleOwner) { uiState ->
            if (uiState is UiState.Success) {
                viewModel.onLoadTrailerSuccess(uiState.data)
            }
        }
    }

    private fun handleTVShowCast() {
        viewModel.tvShowCastState.observe(viewLifecycleOwner) { uiState ->
            if (uiState is UiState.Success) {
                itemsList.add(RecyclerViewItem.CastItem(uiState.data))
                tvShowDetailsAdapter.setItems(itemsList)
            }
        }
    }
}

