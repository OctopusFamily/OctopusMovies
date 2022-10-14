package com.octopus.moviesapp.ui.genres

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentGenresBinding
import com.octopus.moviesapp.domain.enums.GenresType
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.movies.MoviesFragmentDirections
import com.octopus.moviesapp.util.observeEvent
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
            if (state is UiState.Success) {
                binding.genresRecyclerView.adapter = GenresAdapter(state.data, viewModel)
            }
        }
        viewModel.navigateToGenreMovie.observeEvent(viewLifecycleOwner) { genre ->
            navigateToMovieGenre(genre)
        }
        viewModel.navigateToGenreTVShow.observeEvent(viewLifecycleOwner) { genre ->
            navigateToTVShowGenre(genre)
        }
    }

    private fun navigateToMovieGenre(genre: Genre) {
        requireView().findNavController()
            .navigate(
                GenresFragmentDirections
                    .actionGenresFragmentToMoviesGenreFragment(genre)
            )
    }

    private fun navigateToTVShowGenre(genre: Genre) {
        requireView().findNavController()
            .navigate(
                GenresFragmentDirections
                    .actionGenresFragmentToTVShowsGenreFragment(genre)
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