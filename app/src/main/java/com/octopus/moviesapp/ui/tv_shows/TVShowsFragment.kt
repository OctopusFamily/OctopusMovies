package com.octopus.moviesapp.ui.tv_shows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentTvShowsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.base.MyLoadStateAdapter
import com.octopus.moviesapp.util.extensions.createChip
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TVShowsFragment : BaseFragment<FragmentTvShowsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tv_shows
    override val viewModel: TVShowsViewModel by viewModels()

    private lateinit var tvShowsAdapter: TVShowsPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setChips()
        initMoviesAdapter()
        observeMainState()
        handleEvents()
    }

    private fun initMoviesAdapter() {
        tvShowsAdapter = TVShowsPagingAdapter(viewModel)
        val footerStateAdapter = MyLoadStateAdapter(tvShowsAdapter::retry)
        binding.tvShowsRecyclerView.adapter = tvShowsAdapter.withLoadStateFooter(footer = footerStateAdapter)
    }

    private fun observeMainState() {
        lifecycleScope.launch {
            viewModel.tvShowsMainUiState.collectLatest {
                it.tvShowsUiState.collectLatest { pagingData ->
                    tvShowsAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun handleEvents() {
        viewModel.navigateToTVShowDetails.observeEvent(viewLifecycleOwner) { tvShowId ->
            navigateToTVShowDetails(tvShowId)
        }
    }

    private fun setChips() {
        binding.tvShowsChipGroup.run {
            createChip(getString(R.string.popular), false)
            createChip(getString(R.string.on_the_air), false)
            createChip(getString(R.string.airing_today), false)
            createChip(getString(R.string.top_rated), false)
        }
    }

    private fun navigateToTVShowDetails(tvShowId: Int) {
        requireView().findNavController().navigate(TVShowsFragmentDirections.actionTVShowsFragmentToTVShowDetailsFragment(tvShowId))
    }
}