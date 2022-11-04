package com.octopus.moviesapp.android.usecases.mapper

import com.octopus.moviesapp.android.remote.response.dto.GenreDTO
import com.octopus.moviesapp.models.model.Genre
import com.octopus.moviesapp.repositories.repository.type.GenresType
import javax.inject.Inject

class GenresMapper @Inject constructor() : Mapper<Pair<List<GenreDTO>, GenresType>, List<Genre>>() {
    override fun map(input: Pair<List<GenreDTO>, GenresType>): List<Genre> {
        return if (input.first.isEmpty()) emptyList()
        else input.first.map {
            Genre(
                id = it.id ?: 0,
                name = it.name ?: "",
                type = input.second,
            )
        }
    }
}