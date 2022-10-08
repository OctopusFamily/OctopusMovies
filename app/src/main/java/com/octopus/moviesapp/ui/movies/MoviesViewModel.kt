package com.octopus.moviesapp.ui.movies

import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel(), MoviesClicksListener {
    override fun onMovieClick(movieId: Int) {

    }
}