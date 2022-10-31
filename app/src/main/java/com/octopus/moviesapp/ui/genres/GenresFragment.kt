package com.octopus.moviesapp.ui.genres

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentGenresBinding
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenresFragment : BaseFragment<FragmentGenresBinding>(), TabLayout.OnTabSelectedListener {
    override fun getLayoutId(): Int = R.layout.fragment_genres
    override val viewModel: GenresViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
        handleTabLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onTapSelected(GenresType.MOVIE)
    }

    private fun handleEvents() {
        lifecycleScope.launch {
            viewModel.genresListState.collect { state ->
                if (state.isSuccess) {
                    binding.genresRecyclerView.adapter = GenresAdapter(state.genres, viewModel)
                }
            }
        }
        viewModel.apply {
            navigateToGenreMovie.observeEvent(viewLifecycleOwner) { genre ->
                navigateToMovieGenre(genre)
            }
            navigateToGenreTVShow.observeEvent(viewLifecycleOwner) { genre ->
                navigateToTVShowGenre(genre)
            }
        }
    }

    private fun navigateToMovieGenre(genreId: Int) {
        requireView().findNavController()
            .navigate(
                GenresFragmentDirections
                    .actionGenresFragmentToMoviesGenreFragment(genreId)
            )
    }

    private fun navigateToTVShowGenre(genreId: Int) {
        requireView().findNavController()
            .navigate(
                GenresFragmentDirections
                    .actionGenresFragmentToTVShowsGenreFragment(genreId)
            )
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

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}
}