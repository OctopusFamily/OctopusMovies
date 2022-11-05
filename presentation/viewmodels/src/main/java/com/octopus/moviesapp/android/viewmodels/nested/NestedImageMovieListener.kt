package com.octopus.moviesapp.android.viewmodels.nested

import com.octopus.moviesapp.android.viewmodels.base.BaseInteractionListener

interface NestedImageMovieListener : BaseInteractionListener {
    fun onImageMovieClick(movieId: Int)
}