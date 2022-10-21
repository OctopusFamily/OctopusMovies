package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.ui.base.BaseAdapter

class NestedCastAdapter(
    itemsList: List<Cast>,
    listener: NestedCastListener,
) : BaseAdapter<Cast>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_nested_cast
    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.id == newItem.id
    }
}