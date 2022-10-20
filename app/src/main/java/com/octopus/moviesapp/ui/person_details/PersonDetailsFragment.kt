package com.octopus.moviesapp.ui.person_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentActorDetailsBinding
import com.octopus.moviesapp.databinding.FragmentMoviesBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<FragmentActorDetailsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_actor_details
    override val viewModel: PersonDetailsViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    private val args: PersonDetailsFragmentArgs by navArgs()
}