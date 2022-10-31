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
class GenresFragment : BaseFragment<FragmentGenresBinding>(){
    override fun getLayoutId(): Int = R.layout.fragment_genres
    override val viewModel: GenresViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        lifecycleScope.launch {
            viewModel.genresState.collect { state ->
                if (state.isSuccess) {
                    binding.genresRecyclerView.adapter = GenresAdapter(state.genres, viewModel)
                }
            }
        }
        viewModel.apply {
            navigateToGenreMovie.observeEvent(viewLifecycleOwner) {
                navigateToMovieGenre(it.first, it.second)
            }
            navigateToGenreTVShow.observeEvent(viewLifecycleOwner) {
                navigateToTVShowGenre(it.first, it.second)
            }
        }
    }

    private fun navigateToMovieGenre(genreId: Int, genreName: String) {
        requireView().findNavController()
            .navigate(
                GenresFragmentDirections
                    .actionGenresFragmentToMoviesGenreFragment(genreId, genreName)
            )
    }

    private fun navigateToTVShowGenre(genreId: Int, genreName: String) {
        requireView().findNavController()
            .navigate(
                GenresFragmentDirections
                    .actionGenresFragmentToTVShowsGenreFragment(genreId, genreName)
            )
    }
}