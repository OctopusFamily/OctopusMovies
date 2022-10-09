package com.octopus.moviesapp.ui.tv_shows

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseAdapter

class TVShowsAdapter(
    private val tvShows: List<TVShow>,
    private val listener: TVShowsClicksListener
) : BaseAdapter<TVShow>(tvShows, listener) {
    override fun layoutId(): Int = R.layout.item_tv_show

}