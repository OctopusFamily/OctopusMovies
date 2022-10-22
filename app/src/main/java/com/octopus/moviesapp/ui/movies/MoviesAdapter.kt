package com.octopus.moviesapp.ui.movies

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.base.BaseAdapter

class MoviesAdapter(
    movies: List<Movie>,
    listener: MoviesClicksListener
) : BaseAdapter<Movie>(movies, listener) {
    override fun layoutId(): Int = R.layout.item_movie
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}