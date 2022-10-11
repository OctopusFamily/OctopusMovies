package com.octopus.moviesapp.ui.tv_shows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentTvShowsBinding
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.movies.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowsFragment : BaseFragment<FragmentTvShowsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tv_shows
    override val viewModel: TVShowsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.tvShowsListState.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success) {
                binding.tvShowsRecyclerView.adapter = TVShowsAdapter(state.data, viewModel)
            }
        }
    }
}