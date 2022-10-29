package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.tv_show_details.uistate.GenresUiState

class NestedGenresAdapter(
    itemsList: List<GenresUiState>,
    listener: NestedGenresListener,
) : BaseAdapter<GenresUiState>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_nested_genre
    override fun areContentsTheSame(
        oldItem: GenresUiState,
        newItem: GenresUiState
    ): Boolean {
        return oldItem.id == newItem.id
    }
}