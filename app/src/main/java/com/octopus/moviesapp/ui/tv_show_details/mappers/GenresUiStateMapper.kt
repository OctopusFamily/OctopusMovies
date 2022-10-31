package com.octopus.moviesapp.ui.tv_show_details.mappers

import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.ui.tv_show_details.uistate.GenresUiState
import javax.inject.Inject

class GenresUiStateMapper  @Inject constructor(){
     fun map(input: Pair<List<Genre>, GenresType>): List<GenresUiState> {
        return if (input.first.isEmpty()) emptyList()
        else input.first.map {
            GenresUiState(
                id = it.id ?: 0,
                name = it.name ?: "",
                type = input.second,
            )
        }
    }
}