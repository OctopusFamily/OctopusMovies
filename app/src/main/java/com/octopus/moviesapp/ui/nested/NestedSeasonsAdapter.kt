package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowDetailsState.TVShowSeasonUiState

class NestedSeasonsAdapter(
    itemsList: List<TVShowSeasonUiState>,
    listener: NestedSeasonsListener,
) : BaseAdapter<TVShowSeasonUiState>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_nested_season
    override fun areContentsTheSame(
        oldItem: TVShowSeasonUiState,
        newItem: TVShowSeasonUiState
    ): Boolean {
        return oldItem.id == newItem.id
    }
}