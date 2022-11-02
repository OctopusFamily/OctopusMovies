package com.octopus.moviesapp.ui.search

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.search.uistate.SearchResultUiState

class SearchAdapter (
    item: List<SearchResultUiState>,
    listener: SearchClicksListener,
) : BaseAdapter<SearchResultUiState>(item, listener) {
    override fun layoutId(): Int = R.layout.item_search
    override fun areContentsTheSame(oldItem: SearchResultUiState, newItem: SearchResultUiState): Boolean {
        return oldItem.id == newItem.id
    }
}