package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertStringToDate
import javax.inject.Inject

class MoviesMapper @Inject constructor() : Mapper<MovieDTO, Movie>() {
    override fun map(input: MovieDTO): Movie {
        return Movie(
            id = input.id ?: 0,
            title = input.originalTitle ?: "",
            posterImageUrl = buildImageUrl(input.posterPath),
            voteAverage = input.voteAverage ?: 0f,
            releaseDate = convertStringToDate(input.releaseDate),
        )
    }
}