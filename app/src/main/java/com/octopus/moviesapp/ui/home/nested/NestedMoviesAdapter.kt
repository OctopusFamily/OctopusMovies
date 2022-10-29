package com.octopus.moviesapp.ui.home.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState

class NestedMoviesAdapter(
    itemsList: List<MovieUiState>,
    listener: MoviesClicksListener,
) : BaseAdapter<MovieUiState>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_nested_movie

    override fun areContentsTheSame(oldItem: MovieUiState, newItem: MovieUiState): Boolean {
        return oldItem.id == newItem.id
    }
}