package com.octopus.moviesapp.ui.tv_shows_genre

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentTvShowsGenreBinding
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import com.octopus.moviesapp.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowsGenreFragment : BaseFragment<FragmentTvShowsGenreBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tv_shows_genre
    override val viewModel: TVShowsGenreViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE
    private  lateinit var tvShowsGenreAdapter : TVShowsGenreAdapter

    private val args: TVShowsGenreFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTVShow(args.genre.id, args.genre.name)
       handleEvents()
        setAdapter()

    }

    private fun setAdapter(){
        viewModel.tvShowGenreState.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success) {
                tvShowsGenreAdapter = TVShowsGenreAdapter(state.data, viewModel)
                binding.tvShowRecyclerView.adapter = tvShowsGenreAdapter
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