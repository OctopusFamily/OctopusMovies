package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.base.BaseAdapter

class NestedImageMovieAdapter(
    movies: List<Movie>,
    listener: NestedImageMovieListener,
) : BaseAdapter<Movie>(movies, listener) {
    override fun layoutId(): Int = R.layout.item_image_movie
}