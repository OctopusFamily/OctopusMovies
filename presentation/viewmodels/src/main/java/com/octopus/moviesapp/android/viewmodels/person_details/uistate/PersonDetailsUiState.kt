package com.octopus.moviesapp.android.viewmodels.person_details.uistate

data class PersonDetailsUiState(
    val name: String = "",
    val profilePath: String = "",
    val biography: String = "",
    val birthday: String = "",
    val knownForDepartment: String = "",
    val popularity: Float = 0F,
    val placeOfBirth: String = ""
)

