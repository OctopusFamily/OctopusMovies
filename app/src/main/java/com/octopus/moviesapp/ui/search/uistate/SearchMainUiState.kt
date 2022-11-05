package com.octopus.moviesapp.ui.search.uistate

import com.octopus.moviesapp.android.local.types.MediaType
import com.octopus.moviesapp.android.viewmodels.search.uistate.SearchResultUiState

data class SearchMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val searchResults: List<SearchResultUiState> = emptyList(),
    val mediaType: MediaType = MediaType.MOVIE,
    val query: String = "",
)
