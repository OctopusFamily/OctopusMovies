package com.octopus.moviesapp.android.viewmodels.search.mapper

import com.octopus.moviesapp.android.viewmodels.search.uistate.SearchResultUiState
import com.octopus.moviesapp.models.model.SearchResult

fun SearchResult.asSearchResultUiState(): SearchResultUiState {
    return SearchResultUiState(
        id = id,
        imageUrl = posterImageUrl,
        name = title,
    )
}
