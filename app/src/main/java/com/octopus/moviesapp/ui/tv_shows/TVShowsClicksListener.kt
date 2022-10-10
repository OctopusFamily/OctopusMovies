package com.octopus.moviesapp.ui.tv_shows

import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface TVShowsClicksListener : BaseInteractionListener {
    fun onTVShowClick(tvShow: TVShow)
}