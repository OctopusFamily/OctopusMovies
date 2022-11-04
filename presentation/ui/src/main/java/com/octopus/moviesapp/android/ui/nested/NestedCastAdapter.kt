package com.octopus.moviesapp.android.ui.nested

import com.octopus.moviesapp.android.ui.base.BaseAdapter
import com.octopus.moviesapp.android.viewmodels.nested.NestedCastListener

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