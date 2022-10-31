package com.octopus.moviesapp.ui.home.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState

class NestedTVShowsAdapter(
    itemsList: List<TVShowUiState>,
    listener: TVShowsClicksListener,
) : BaseAdapter<TVShowUiState>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_tv_show

    override fun areContentsTheSame(oldItem: TVShowUiState, newItem: TVShowUiState): Boolean {
        return oldItem.id == newItem.id
    }
}