package com.octopus.moviesapp.ui.genres.uistate

data class GenresMainUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val genres: List<GenresUiState> = emptyList(),
)