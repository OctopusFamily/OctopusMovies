package com.octopus.moviesapp.ui.person_details.uistate

data class PersonDetailsUiState(
    val name: String,
    val profilePath: String,
    val biography: String,
    val birthday: String,
    val knownForDepartment: String,
    val popularity: Float,
    val placeOfBirth: String
)

