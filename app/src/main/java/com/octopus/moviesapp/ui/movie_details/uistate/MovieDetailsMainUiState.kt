package com.octopus.moviesapp.ui.movie_details.uistate

import com.octopus.moviesapp.ui.tv_show_details.uistate.cast_uistate.CastUiState

data class MovieDetailsMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess : Boolean = false,
    val movieDetailsUiState: MovieDetailsUiState = MovieDetailsUiState(),
    val movieCastUiState: List<CastUiState> = emptyList(),

    )
