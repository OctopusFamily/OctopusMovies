package com.octopus.moviesapp.ui.movie_details.uistate

data class MovieDetailsMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess : Boolean = false,
    val tvShowDetailsUiState: MovieDetailsUiState = MovieDetailsUiState()

)
