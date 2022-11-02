package com.octopus.moviesapp.ui.genres.uistate

import com.octopus.moviesapp.domain.types.GenresType

data class GenresMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val selectedTab: Pair<Int, GenresType> = Pair(0, GenresType.MOVIE),
    val genres: List<GenresUiState> = emptyList(),
)