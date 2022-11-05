package com.octopus.moviesapp.android.viewmodels.search.uistate

import com.octopus.moviesapp.models.type.MediaType

data class SearchMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val searchResults: List<SearchResultUiState> = emptyList(),
    val mediaType: MediaType = MediaType.MOVIE,
    val query: String = "",
)
