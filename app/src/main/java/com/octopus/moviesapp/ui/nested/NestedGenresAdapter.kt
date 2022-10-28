package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowDetailsState.TVShowDetailsGenresUiState

class NestedGenresAdapter(
    itemsList: List<TVShowDetailsGenresUiState>,
    listener: NestedGenresListener,
) : BaseAdapter<TVShowDetailsGenresUiState>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_nested_genre
    override fun areContentsTheSame(
        oldItem: TVShowDetailsGenresUiState,
        newItem: TVShowDetailsGenresUiState
    ): Boolean {
        return oldItem.id == newItem.id
    }
}