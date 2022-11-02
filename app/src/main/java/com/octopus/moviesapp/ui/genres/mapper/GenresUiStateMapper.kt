package com.octopus.moviesapp.ui.genres.mapper

import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.ui.genres.uistate.GenresUiState
import javax.inject.Inject

class GenresUiStateMapper @Inject constructor() {
    fun map(input: Pair<List<Genre>, GenresType>): List<GenresUiState> {
        return input.first.map {
            GenresUiState(
                id = it.id,
                name = it.name,
                type = input.second,
            )
        }
    }
}