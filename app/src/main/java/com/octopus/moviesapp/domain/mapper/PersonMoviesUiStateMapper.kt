package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.person_details.uistate.PersonMoviesUiState
import javax.inject.Inject


class PersonMoviesUiStateMapper @Inject constructor() {
    fun map(input: List<Movie>): List<PersonMoviesUiState> {
        return input.map {
            PersonMoviesUiState(
                posterImageUrl = it.posterImageUrl
            )
        }
    }
}