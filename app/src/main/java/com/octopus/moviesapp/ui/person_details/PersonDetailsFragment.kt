package com.octopus.moviesapp.ui.person_details

import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMoviesBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<FragmentMoviesBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_actor_details
    override val viewModel: PersonDetailsViewModel by viewModels()
}