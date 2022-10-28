package com.octopus.moviesapp.ui.movies

import androidx.recyclerview.widget.DiffUtil
import com.octopus.moviesapp.R
import com.octopus.moviesapp.ui.base.BasePagingAdapter
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState

class MoviesPagingAdapter(
    listener: MoviesClicksListener,
) : BasePagingAdapter<MovieUiState>(listener, differCallback) {

    override fun layoutId(): Int  = R.layout.item_movie

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<MovieUiState>() {
            override fun areItemsTheSame(oldItem: MovieUiState, newItem: MovieUiState): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieUiState, newItem: MovieUiState): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}