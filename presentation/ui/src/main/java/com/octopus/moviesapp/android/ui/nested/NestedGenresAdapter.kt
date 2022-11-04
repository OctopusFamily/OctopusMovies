package com.octopus.moviesapp.android.ui.nested

import com.octopus.moviesapp.android.ui.base.BaseAdapter
import com.octopus.moviesapp.android.viewmodels.nested.NestedGenresListener

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