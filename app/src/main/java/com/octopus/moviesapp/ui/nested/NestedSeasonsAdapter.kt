package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.tv_show_details.uistate.SeasonUiState

class NestedSeasonsAdapter(
    itemsList: List<SeasonUiState>,
    listener: NestedSeasonsListener,
) : BaseAdapter<SeasonUiState>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_nested_season
    override fun areContentsTheSame(
        oldItem: SeasonUiState,
        newItem: SeasonUiState
    ): Boolean {
        return oldItem.id == newItem.id
    }
}