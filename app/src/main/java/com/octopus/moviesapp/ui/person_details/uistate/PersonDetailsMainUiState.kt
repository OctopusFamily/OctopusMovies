package com.octopus.moviesapp.ui.person_details.uistate

data class PersonDetailsMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val personDetailsUiState: PersonDetailsUiState = PersonDetailsUiState(
        "", "",
        "",
        "",
        "",
        0F,
        "",
    )
)

