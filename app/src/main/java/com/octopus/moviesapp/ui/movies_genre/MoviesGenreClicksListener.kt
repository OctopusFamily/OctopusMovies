package com.octopus.moviesapp.ui.movies_genre

import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface MoviesGenreClicksListener : BaseInteractionListener {
    fun onMovieClick(MovieId: Int)
}