package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertToDate
import java.util.*
import javax.inject.Inject

class MoviesMapper @Inject constructor() : Mapper<MovieDTO, Movie> {
    override fun map(input: MovieDTO): Movie {
        return Movie(
            id = input.id ?: 0,
            title = input.title ?: "",
            posterImageUrl = input.posterPath?.buildImageUrl() ?: "",
            voteAverage = input.voteAverage ?: 0f,
            releaseDate = input.releaseDate?.convertToDate() ?: Date(),
        )
    }
}