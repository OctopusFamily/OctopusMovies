package com.octopus.moviesapp.ui.genres

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentGenresBinding
import com.octopus.moviesapp.domain.enums.GenresType
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenresFragment : BaseFragment<FragmentGenresBinding>(), TabLayout.OnTabSelectedListener {
    override fun getLayoutId(): Int = R.layout.fragment_genres
    override val viewModel: GenresViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
        handleTabLayout()
    }

    private fun handleEvents() {
        viewModel.genresListState.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success){
                binding.genresRecyclerView.adapter = GenresAdapter(state.data, viewModel)
            }
        }
    }

    private fun handleTabLayout() {
        binding.genresTabLayout.addOnTabSelectedListener(this)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.position) {
            0 -> {
                viewModel.onTapSelected(GenresType.MOVIE)
            }
            1 -> {
                viewModel.onTapSelected(GenresType.TV)
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) { }

    override fun onTabReselected(tab: TabLayout.Tab?) { }
}