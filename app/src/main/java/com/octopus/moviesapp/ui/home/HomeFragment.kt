package com.octopus.moviesapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.di.app.Application
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentHomeBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.RecyclerViewItem
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    private lateinit var homeAdapter: HomeAdapter
    private val homeAdapterItems = mutableListOf<RecyclerViewItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()
        observeLiveData()
        handleEvents()
        setOnBackButtonClickListener()
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(emptyList(), viewModel, viewModel)
        binding.homeRecyclerView.adapter = homeAdapter
    }

    private fun observeLiveData() {
        observeTrendingMoviesState()
        observeTrendingTVShowsState()
        observeTrendingPeopleState()
        viewModel.isTextClicked.observeEvent(viewLifecycleOwner){
            if (it){
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllListsFragment(
                    Application.sessionId))
            }
        }
        observeRecommendedMoviesState()
        observeRecommendedTVShowsState()
    }

    private fun observeTrendingMoviesState() {
        viewModel.trendingMoviesState.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.let { trendingList ->
                homeAdapterItems.add(0, RecyclerViewItem.ImageSliderItem(getString(R.string.trending_movies), trendingList))
                homeAdapter.setItems(homeAdapterItems)
            }
        }
    }

    private fun observeTrendingTVShowsState() {
        viewModel.trendingTVShowsState.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.let { trendingList ->
                homeAdapterItems.add(RecyclerViewItem.ImageSliderItem(getString(R.string.trending_tv_shows), trendingList))
                homeAdapter.setItems(homeAdapterItems)
            }
        }
    }

    private fun observeTrendingPeopleState() {
        viewModel.trendingPeopleState.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.let { trendingList ->
                homeAdapterItems.add(RecyclerViewItem.TrendingPeopleItem(trendingList))
                homeAdapter.setItems(homeAdapterItems)
            }
        }
    }

    private fun observeRecommendedMoviesState() {
        viewModel.recommendedMovies.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.let { moviesList ->
                homeAdapterItems.add(RecyclerViewItem.MoviesItem(moviesList))
                homeAdapter.setItems(homeAdapterItems)
            }
        }
    }

    private fun observeRecommendedTVShowsState() {
        viewModel.recommendedTVShows.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.let { tvShowsList ->
                homeAdapterItems.add(RecyclerViewItem.TVShowsItem(tvShowsList))
                homeAdapter.setItems(homeAdapterItems)
            }
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