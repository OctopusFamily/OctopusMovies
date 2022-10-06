package com.octopus.moviesapp.ui.genres

import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentGenresBinding
import com.octopus.moviesapp.ui.base.BaseFragment

class GenresFragment : BaseFragment<FragmentGenresBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_genres
    override val viewModel: GenresViewModel by viewModels()
}