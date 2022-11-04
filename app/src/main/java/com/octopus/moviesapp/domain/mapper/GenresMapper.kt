package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.android.response.dto.GenreDTO
import com.octopus.moviesapp.android.local.types.GenresType
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