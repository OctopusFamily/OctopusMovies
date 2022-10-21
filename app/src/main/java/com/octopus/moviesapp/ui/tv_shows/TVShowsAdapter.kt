package com.octopus.moviesapp.ui.tv_shows

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseAdapter

class TVShowsAdapter(
    tvShows: List<TVShow>,
    listener: TVShowsClicksListener,
) : BaseAdapter<TVShow>(tvShows, listener) {
    override fun layoutId(): Int = R.layout.item_tv_show
    override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem.id == newItem.id
    }

}