package com.octopus.moviesapp.ui.home

import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.databinding.FragmentHomeBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
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