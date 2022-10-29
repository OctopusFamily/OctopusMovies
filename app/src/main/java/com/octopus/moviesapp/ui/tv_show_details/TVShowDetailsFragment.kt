package com.octopus.moviesapp.ui.tv_show_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentTvShowDetailsBinding
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.RecyclerViewItem
import com.octopus.moviesapp.util.extensions.navigateToTrailerActivity
import com.octopus.moviesapp.util.extensions.observeEvent
import com.octopus.moviesapp.util.extensions.showShortToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TVShowDetailsFragment : BaseFragment<FragmentTvShowDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tv_show_details
    override val viewModel: TVShowDetailsViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE


    private val itemsList = mutableListOf<RecyclerViewItem>()
    private lateinit var tvShowDetailsAdapter: TVShowDetailsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowDetailsAdapter = TVShowDetailsAdapter(
            itemsList,
            viewModel,
            viewModel,
            viewModel,
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
        viewModel.navigateToTVShowsGenre.observeEvent(viewLifecycleOwner) { genre ->
            navigateToTVShowsGenreFragment(genre)
        }

        viewModel.navigateToPersonDetails.observeEvent(viewLifecycleOwner){ castId ->
            navigateToPersonDetailsFragment(castId)
        }
    }

    private fun handleTVShowDetails() {
        lifecycleScope.launch {
            viewModel.tvShowDetailsState.collect{ uiState ->
                if (uiState.isSuccess) {
                    viewModel.onLoadTVShowDetailsSuccess(uiState.Info)
                    itemsList.add(0, RecyclerViewItem.TVShowInfoItem(uiState.Info))
                    itemsList.add(1, RecyclerViewItem.SeasonItem(uiState.Info.seasons))
                    tvShowDetailsAdapter.setItems(itemsList)
                    binding.tvShowDetailsRecyclerView.adapter = tvShowDetailsAdapter
                }
            }

        }

    }

    private fun observeTrailerState() {
            lifecycleScope.launch {
            viewModel.tvShowDetailsState.collect { uiState ->
                if (uiState.isSuccess) {
                    viewModel.onLoadTrailerSuccess(uiState.trailer)
                }
            }
        }
    }

    private fun handleTVShowCast() {
       lifecycleScope.launch {
           viewModel.tvShowDetailsState.collect{ uiState ->
               if (uiState.isSuccess) {
                   itemsList.add(RecyclerViewItem.CastItem(uiState.cast))
                   tvShowDetailsAdapter.setItems(itemsList)
               }
           }
       }

    }

    private fun navigateToTVShowsGenreFragment(genre: Genre){
        requireView().findNavController()
            .navigate(TVShowDetailsFragmentDirections.actionTVShowDetailsFragmentToTVShowsGenreFragment(genre))
    }

    private fun navigateToPersonDetailsFragment(castId: Int){
        requireView().findNavController()
            .navigate(TVShowDetailsFragmentDirections.actionTVShowDetailsFragmentToPersonDetailsFragment(castId))
    }
}

