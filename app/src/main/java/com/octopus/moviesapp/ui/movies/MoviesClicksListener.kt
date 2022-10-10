package com.octopus.moviesapp.ui.movies

import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface MoviesClicksListener : BaseInteractionListener {
    fun onMovieClick(movies: Movie)
}