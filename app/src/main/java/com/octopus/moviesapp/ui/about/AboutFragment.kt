package com.octopus.moviesapp.ui.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentAboutBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.extensions.observeEvent
import com.octopus.moviesapp.util.extensions.openUrlInBrowser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : BaseFragment<FragmentAboutBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_about
    override val viewModel: AboutViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.navigateBack.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        viewModel.openGithubLinkInBrowser.observeEvent(viewLifecycleOwner) {
            requireContext().openUrlInBrowser(Constants.GITHUB_URL)
        }
    }

}
