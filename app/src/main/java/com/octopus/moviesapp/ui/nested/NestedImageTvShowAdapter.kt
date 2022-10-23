package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseAdapter

class NestedImageTvShowAdapter(
    tvShows: List<TVShow>,
    listener: NestedImageTvShowListener,
) : BaseAdapter<TVShow>(tvShows, listener) {
    override fun layoutId(): Int = R.layout.item_image_tv_show
    override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem.id == newItem.id
    }
}