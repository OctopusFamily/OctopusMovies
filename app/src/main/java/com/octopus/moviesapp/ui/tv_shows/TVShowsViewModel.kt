package com.octopus.moviesapp.ui.tv_shows

import com.octopus.moviesapp.domain.repository.MainRepository
import com.octopus.moviesapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel() {
}