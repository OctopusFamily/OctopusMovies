package com.octopus.moviesapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()
        observeLiveData()
        handleEvents()
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(emptyList(), viewModel)
        binding.homeRecyclerView.adapter = homeAdapter
    }

    private fun observeLiveData() {
        viewModel.run {
            trendingMoviesState.observe(viewLifecycleOwner) { uiState ->
                Log.d("MALT", "STATE FROM FRAGMENT IS: $uiState")
                uiState.toData()?.let { trendingList ->
                    val recyclerViewItems = listOf(
                        RecyclerViewItem.ImageSliderItem("Movies", trendingList)
                    )
                    homeAdapter.setItems(recyclerViewItems)
                }
            }
        }
    }

    private fun handleEvents() {
        viewModel.navigateSearch.observeEvent(viewLifecycleOwner) {
            navigateToSearch()
        }
    }

    private fun navigateToSearch() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
    }
}