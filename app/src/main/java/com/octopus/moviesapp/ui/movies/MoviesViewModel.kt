package com.octopus.moviesapp.ui.movies

import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.domain.enums.MoviesType

class MoviesViewModel : ViewModel(), MoviesClicksListener {
    private var currentMovieType = MoviesType.POPULAR

    override fun onMovieClick(movieId: Int) {}

    fun onChipClick(moviesType: MoviesType) {
        if (moviesType != currentMovieType) {
            getMoviesByType(moviesType)
            currentMovieType = moviesType
        }
    }

    private fun getMoviesByType(moviesType: MoviesType) {}
}