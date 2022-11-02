package com.octopus.moviesapp.ui.search.mapper

import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.ui.search.uistate.SearchResultUiState

fun List<SearchResult>.asSearchResultUiState(): List<SearchResultUiState> {
    return this.map {
        SearchResultUiState(
            id = it.id,
            imageUrl = it.posterImageUrl,
            name = it.title,
        )
    }
}
