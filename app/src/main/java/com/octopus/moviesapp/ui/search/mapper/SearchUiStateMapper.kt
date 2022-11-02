package com.octopus.moviesapp.ui.search.mapper

import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.ui.search.uistate.SearchResultUiState

fun SearchResult.asSearchResultUiState(): SearchResultUiState {
    return SearchResultUiState(
        id = id,
        imageUrl = posterImageUrl,
        name = title,
    )
}
