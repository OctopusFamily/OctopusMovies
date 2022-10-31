package com.octopus.moviesapp.ui.movie_details.uistate

import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.TrailerUiState

data class MovieDetailsMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val info: MovieDetailsUiState = MovieDetailsUiState(),
    val trailer: TrailerUiState = TrailerUiState(),
    val cast: List<CastUiState> = emptyList(),
)