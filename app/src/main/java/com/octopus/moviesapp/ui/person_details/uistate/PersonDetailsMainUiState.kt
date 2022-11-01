package com.octopus.moviesapp.ui.person_details.uistate

data class PersonDetailsMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val detailsUiState: PersonDetailsUiState = PersonDetailsUiState(),
    val moviesUiState: List<PersonMovieUiState> = emptyList(),
    val TVShowUiState: List<PersonTVShowUiState> = emptyList(),
)

