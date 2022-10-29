package com.octopus.moviesapp.ui.tv_shows

import androidx.recyclerview.widget.DiffUtil
import com.octopus.moviesapp.R
import com.octopus.moviesapp.ui.base.BasePagingAdapter
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState

class TVShowsPagingAdapter(
    listener: TVShowsClicksListener,
) : BasePagingAdapter<TVShowUiState>(listener, differCallback) {
    override fun layoutId(): Int  = R.layout.item_tv_show

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<TVShowUiState>() {
            override fun areItemsTheSame(oldItem: TVShowUiState, newItem: TVShowUiState): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TVShowUiState, newItem: TVShowUiState): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}