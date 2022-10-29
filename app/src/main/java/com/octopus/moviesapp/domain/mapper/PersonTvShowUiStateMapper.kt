package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.person_details.uistate.PersonTvShowUiState
import javax.inject.Inject


class PersonTvShowUiStateMapper @Inject constructor() {
    fun map(input: List<TVShow>): List<PersonTvShowUiState> {
        return input.map {
            PersonTvShowUiState(
                posterImageUrl = it.posterImageUrl
            )
        }
    }
}


