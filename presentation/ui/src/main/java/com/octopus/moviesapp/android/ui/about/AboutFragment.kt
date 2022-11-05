package com.octopus.moviesapp.android.ui.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.android.viewmodels.about.AboutViewModel
import com.octopus.moviesapp.databinding.FragmentAboutBinding
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
            requireContext().openUrlInBrowser(GITHUB_URL)
        }
    }

    private fun Context.openUrlInBrowser(url: String) {
        Intent(Intent.ACTION_VIEW).run {
            data = Uri.parse(url)
            startActivity(this)
        }
    }

    const val GITHUB_URL = "https://github.com/OctopusFamily/OctopusMovies"

}
