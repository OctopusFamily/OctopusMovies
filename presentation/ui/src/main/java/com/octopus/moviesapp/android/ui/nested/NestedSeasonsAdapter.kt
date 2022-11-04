package com.octopus.moviesapp.android.ui.nested


import com.octopus.moviesapp.android.ui.base.BaseAdapter
import com.octopus.moviesapp.android.viewmodels.nested.NestedSeasonsListener

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