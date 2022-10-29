package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState

class NestedCastAdapter(
    itemsList: List<CastUiState>,
    listener: NestedCastListener,
) : BaseAdapter<CastUiState>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_nested_cast
    override fun areContentsTheSame(
        oldItem: CastUiState,
        newItem: CastUiState
    ): Boolean {
        return oldItem.id == newItem.id
    }
}