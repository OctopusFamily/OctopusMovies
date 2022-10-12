package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertToDate
import java.util.*
import javax.inject.Inject

class MoviesMapper @Inject constructor() : Mapper<List<MovieDTO>, List<Movie>> {
    override fun map(input: List<MovieDTO>): List<Movie> {
        return input.map {
            Movie(
                id = it.id ?: 0,
                title = it.originalTitle ?: "",
                posterImageUrl = it.posterPath ?.buildImageUrl() ?: "",
                voteAverage = it.voteAverage ?: 0f,
                releaseDate = it.releaseDate?.convertToDate() ?: Date(),
            )
        }
    }
}