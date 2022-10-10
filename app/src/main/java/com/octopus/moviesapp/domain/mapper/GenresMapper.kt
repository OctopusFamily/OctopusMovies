package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.GenreDTO
import com.octopus.moviesapp.domain.enums.GenresList
import com.octopus.moviesapp.domain.model.Genre

class GenresMapper : DuoMapper<List<GenreDTO>, GenresList, List<Genre>> {
    override fun map(firstInput: List<GenreDTO>, secondInput: GenresList): List<Genre> {
        return firstInput.map {
            Genre(
                id = it.id ?: 0,
                name = it.name ?: "",
                type = secondInput,
            )
        }
    }
}