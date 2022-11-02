package com.octopus.moviesapp.ui.tv_shows_genre

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentTvShowsGenreBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.base.MyLoadStateAdapter
import com.octopus.moviesapp.ui.tv_shows.TVShowsPagingAdapter
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TVShowsGenreFragment : BaseFragment<FragmentTvShowsGenreBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tv_shows_genre
    override val viewModel: TVShowsGenreViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    private lateinit var tvShowsAdapter: TVShowsPagingAdapter

    private val args: TVShowsGenreFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTVShow(args.genreId, args.genreName)
        handleEvents()
        initMoviesAdapter()
        observeMainState()
    }

    private fun initMoviesAdapter() {
        tvShowsAdapter = TVShowsPagingAdapter(viewModel)
        val footerStateAdapter = MyLoadStateAdapter(tvShowsAdapter::retry)
        binding.tvShowRecyclerView.adapter = tvShowsAdapter.withLoadStateFooter(footer = footerStateAdapter)
    }

    private fun observeMainState() {
        lifecycleScope.launch {
            viewModel.tvShowGenreState.collectLatest {
                it.tvShowsUiState.collectLatest { pagingData ->
                    tvShowsAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun handleEvents() {

        viewModel.navigateToTVShowDetails.observeEvent(viewLifecycleOwner) { tvShowbId ->
            navigateToTVShowDetails(tvShowbId)
        }
        viewModel.navigateBack.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

    }

    private fun navigateToTVShowDetails(tvShowId: Int) {
        requireView().findNavController()
            .navigate(TVShowsGenreFragmentDirections
                .actionTVShowsGenreFragmentToTVShowDetailsFragment(tvShowId)
            )
    }

}