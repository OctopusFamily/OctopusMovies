package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.util.buildUrl
import com.octopus.moviesapp.util.convertToDate
import java.util.*

class MoviesMapper : Mapper<List<MovieDTO>, List<Movie>> {
    override fun map(input: List<MovieDTO>): List<Movie> {
        return input.map {
            Movie(
                id = it.id ?: 0,
                title = it.originalTitle ?: "",
                posterImageUrl = it.posterPath ?.buildUrl() ?: "",
                voteAverage = it.voteAverage ?: 0f,
                releaseDate = it.releaseDate?.convertToDate() ?: Date(),
            )
        }
    }
}