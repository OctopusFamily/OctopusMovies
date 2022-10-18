package com.octopus.moviesapp.ui.movies

import androidx.recyclerview.widget.DiffUtil
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.base.BasePagingAdapter

class MoviesAdapter(
    listener: MoviesClicksListener,
) : BasePagingAdapter<Movie>(listener, differCallback) {
    override fun layoutId(): Int = R.layout.item_movie

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}