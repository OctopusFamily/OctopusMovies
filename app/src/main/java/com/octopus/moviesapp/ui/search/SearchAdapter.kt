package com.octopus.moviesapp.ui.search

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.movies.MoviesClicksListener

class SearchAdapter (
    item: List<SearchResult>,
    listener: MoviesClicksListener,
) : BaseAdapter<SearchResult>(item, listener) {
    override fun layoutId(): Int = R.layout.item_search
}