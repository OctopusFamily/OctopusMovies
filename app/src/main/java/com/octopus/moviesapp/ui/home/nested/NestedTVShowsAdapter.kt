package com.octopus.moviesapp.ui.home.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener

class NestedTVShowsAdapter(
    itemsList: List<TVShow>,
    listener: TVShowsClicksListener,
) : BaseAdapter<TVShow>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_tv_show

    override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem.id == newItem.id
    }
}