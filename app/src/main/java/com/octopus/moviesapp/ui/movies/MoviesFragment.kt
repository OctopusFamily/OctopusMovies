package com.octopus.moviesapp.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMoviesBinding
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_movies
    override val viewModel: MoviesViewModel by viewModels()
    private var adapter: MoviesAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = MoviesAdapter(viewModel)
        binding.moviesRecyclerView.adapter = adapter
        collectUiState()
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovies().collectLatest { movies ->
                Log.d("tests", "Movie Fragment $movies")
                adapter?.submitData(movies)
            }
        }
    }

//    private fun handleEvents() {
//        viewModel.moviesListState.observe(viewLifecycleOwner) { state ->
//            if (state is UiState.Success) {
//
//
//            adapter?.submitData(lifecycle, state.data)
//            }
//        }
//    }
}