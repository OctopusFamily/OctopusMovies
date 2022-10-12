package com.octopus.moviesapp.ui.movie_details

import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface MovieDetailsClicksListener : BaseInteractionListener {
    fun onBackPressed()
    fun onSavePressed()
    fun onWatchTrailerPressed()
    fun onRatePressed()
}