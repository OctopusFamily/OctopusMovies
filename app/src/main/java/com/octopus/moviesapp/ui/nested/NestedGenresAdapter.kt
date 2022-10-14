package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.ui.base.BaseAdapter

class NestedGenresAdapter(
    itemsList: List<Genre>,
    listener: NestedGenresListener,
) : BaseAdapter<Genre>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_nested_genre
}