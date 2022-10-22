package com.octopus.moviesapp.ui.home.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.movies.MoviesClicksListener

class NestedMoviesAdapter(
    itemsList: List<Movie>,
    listener: MoviesClicksListener,
) : BaseAdapter<Movie>(itemsList, listener) {
    override fun layoutId(): Int = R.layout.item_nested_movie

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}