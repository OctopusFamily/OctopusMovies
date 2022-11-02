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
import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.DetailsUiState
import com.octopus.moviesapp.util.RecyclerViewItem
import com.octopus.moviesapp.util.extensions.navigateToTrailerActivity
import com.octopus.moviesapp.util.extensions.observeEvent
import com.octopus.moviesapp.util.extensions.showShortToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
        observeTVShowDetailsUiState()
        handleEvents()
        binding.tvShowDetailsRecyclerView.adapter = tvShowDetailsAdapter
    }

    override fun onPause() {
        super.onPause()
        itemsList.clear()
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

        viewModel.navigateToPersonDetails.observeEvent(viewLifecycleOwner) { castId ->
            navigateToPersonDetailsFragment(castId)
        }
    }

    private fun observeTVShowDetailsUiState() {
        lifecycleScope.launch {
            viewModel.tvShowDetailsState.collectLatest {
                setTVShowDetails(it.info)
                setTVShowCast(it.cast)
            }
        }
    }

    private fun setTVShowDetails(tvShowDetails: DetailsUiState) {
        if (tvShowDetails.id != 0) {
            itemsList.add(0, RecyclerViewItem.TVShowInfoItem(tvShowDetails))
            itemsList.add(1, RecyclerViewItem.SeasonItem(tvShowDetails.seasons))
            tvShowDetailsAdapter.setItems(itemsList)

        }
    }

    private fun setTVShowCast(castUiState: List<CastUiState> ) {
        if (castUiState.isNotEmpty()) {
            itemsList.add(RecyclerViewItem.CastItem(castUiState))
            tvShowDetailsAdapter.setItems(itemsList)
        }

    }

    private fun navigateToTVShowsGenreFragment(genre: Genre) {
        requireView().findNavController()
            .navigate(
                TVShowDetailsFragmentDirections.actionTVShowDetailsFragmentToTVShowsGenreFragment(
                    genre.id,
                    genre.name,
                )
            )
    }

    private fun navigateToPersonDetailsFragment(castId: Int) {
        requireView().findNavController()
            .navigate(
                TVShowDetailsFragmentDirections.actionTVShowDetailsFragmentToPersonDetailsFragment(
                    castId
                )
            )
    }
}

