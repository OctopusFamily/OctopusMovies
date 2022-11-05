package com.octopus.moviesapp.android.viewmodels.movies

import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface MoviesClicksListener : BaseInteractionListener {
    fun onMovieClick(movieId: Int)
}