package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface NestedImageMovieListener : BaseInteractionListener {
    fun onImageMovieClick(movieId: Int)
}