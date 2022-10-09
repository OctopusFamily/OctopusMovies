package com.octopus.moviesapp.ui.tv_shows

import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
}