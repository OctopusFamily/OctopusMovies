package com.octopus.moviesapp.ui.genres

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.genres.uistate.GenresUiState

class GenresAdapter(
    genres: List<GenresUiState>,
    listener: GenresClicksListener,
) : BaseAdapter<GenresUiState>(genres, listener) {
    override fun layoutId(): Int = R.layout.item_genre
    override fun areContentsTheSame(oldItem: GenresUiState, newItem: GenresUiState): Boolean {
        return oldItem.id == newItem.id
    }
}