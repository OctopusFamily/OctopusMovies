package com.octopus.moviesapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentHomeBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.home.uistate.TrendingUiState
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState
import com.octopus.moviesapp.util.RecyclerViewItem
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    private lateinit var homeAdapter: HomeAdapter
    private val homeAdapterItems = mutableListOf<RecyclerViewItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()
        observeMainUiState()
        handleEvents()
        setOnBackButtonClickListener()
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(emptyList(), viewModel, viewModel)
        binding.homeRecyclerView.adapter = homeAdapter
    }

    fun observeMainUiState() {
        lifecycleScope.launch {
            viewModel.homeMainUiState.collectLatest {
                setTrendingMovies(it.trendingMoviesUiState)
                setRecommendedMoviesState(it.moviesItemUiState)
                setTrendingTVShows(it.trendingTVShowsUiState)
                setRecommendedTVShowsState(it.tvShowsItemUiState)
                setTrendingPeople(it.trendingPeopleUiState)
            }
        }
    }

    private fun setTrendingMovies(trendingMovies: List<TrendingUiState>) {
        if (trendingMovies.isNotEmpty()) {
            homeAdapterItems.add(RecyclerViewItem.ImageSliderItem(getString(R.string.trending_movies), trendingMovies))
            homeAdapter.setItems(homeAdapterItems)
        }
    }

    private fun setTrendingTVShows(trendingTVShows: List<TrendingUiState>) {
        if (trendingTVShows.isNotEmpty()) {
            homeAdapterItems.add(RecyclerViewItem.ImageSliderItem(getString(R.string.trending_tv_shows), trendingTVShows))
            homeAdapter.setItems(homeAdapterItems)
        }
    }

    private fun setTrendingPeople(trendingPeople: List<TrendingUiState>) {
        if (trendingPeople.isNotEmpty()) {
            homeAdapterItems.add(RecyclerViewItem.TrendingPeopleItem(trendingPeople))
            homeAdapter.setItems(homeAdapterItems)
        }
    }

    private fun setRecommendedMoviesState(movies: List<MovieUiState>) {
        if (movies.isNotEmpty()) {
            homeAdapterItems.add(RecyclerViewItem.MoviesItem(movies))
            homeAdapter.setItems(homeAdapterItems)
        }
    }

    private fun setRecommendedTVShowsState(tvShows: List<TVShowUiState>) {
        if (tvShows.isNotEmpty()) {
            homeAdapterItems.add(RecyclerViewItem.TVShowsItem(tvShows))
            homeAdapter.setItems(homeAdapterItems)
        }
    }

    private fun handleEvents() {
        viewModel.navigateToSearch.observeEvent(viewLifecycleOwner) {
            navigateToSearch()
        }
    }

    private fun navigateToSearch() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
    }

    private fun setOnBackButtonClickListener() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            onBackPressedCallback)
    }
}