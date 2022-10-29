package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.domain.model.PersonDetails
import com.octopus.moviesapp.ui.person_details.uistate.PersonDetailsUiState
import javax.inject.Inject


class PersonDetailsUiStateMapper @Inject constructor() {
    fun map(input: PersonDetails): PersonDetailsUiState {
        return PersonDetailsUiState(
            name = input.name,
            profilePath = input.profilePath,
            biography = input.biography,
            birthday = input.birthday,
            knownForDepartment = input.knownForDepartment,
            popularity = input.popularity,
            placeOfBirth = input.placeOfBirth,
        )

    }
}