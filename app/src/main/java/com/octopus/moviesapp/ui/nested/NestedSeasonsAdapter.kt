package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Season
import com.octopus.moviesapp.ui.base.BaseAdapter

class NestedSeasonsAdapter(
    itemsList: List<Season>,
    listener: NestedSeasonsListener,
) : BaseAdapter<Season>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_nested_season
    override fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean {
        return oldItem.id == newItem.id
    }
}