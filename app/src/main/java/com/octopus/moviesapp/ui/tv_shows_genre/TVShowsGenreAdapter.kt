package com.octopus.moviesapp.ui.tv_shows_genre

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener

class TVShowsGenreAdapter(
    tvShow: List<TVShow>,
    listener: TVShowsClicksListener
) : BaseAdapter<TVShow>(tvShow, listener) {
    override fun layoutId(): Int = R.layout.item_tv_show
}