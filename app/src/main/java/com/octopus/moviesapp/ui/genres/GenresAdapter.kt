package com.octopus.moviesapp.ui.genres

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.ui.base.BaseAdapter

class GenresAdapter (
    genres: List<Genre>,
    listener: GenresClicksListener,
) : BaseAdapter<Genre>(genres, listener) {
    override fun layoutId(): Int = R.layout.item_genre
    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }
}