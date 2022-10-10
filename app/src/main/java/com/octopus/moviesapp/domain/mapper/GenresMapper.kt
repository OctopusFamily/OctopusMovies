package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.GenreDTO
import com.octopus.moviesapp.domain.model.Genre

class GenresMapper : DuoMapper<List<GenreDTO>, List<Genre>, List<Genre>> {
    override fun map(input: List<GenreDTO>, type: List<Genre>): List<Genre> {
        return input.map {
            Genre(
                id = it.id ?: 0,
                name = it.name ?: "",
                type = type,
            )
        }
    }
}