package com.octopus.moviesapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentSearchBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.searchFilter.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success)
                binding.searchRecyclerView.adapter = SearchAdapter(state.data, viewModel)
        }
    }


}




