package com.octopus.moviesapp.ui.tv_show_details

import com.octopus.moviesapp.data.repository.MainRepository
import com.octopus.moviesapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val repository: MainRepository,
) : BaseViewModel() {
}