package com.octopus.moviesapp.ui.movies

import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface MoviesClicksListener : BaseInteractionListener {
    fun onMovieClick(movieId: Int)
}