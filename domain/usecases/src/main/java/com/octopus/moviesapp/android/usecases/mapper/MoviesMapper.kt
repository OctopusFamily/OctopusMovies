package com.octopus.moviesapp.android.usecases.mapper

import com.octopus.moviesapp.android.remote.response.dto.MovieDTO
import com.octopus.moviesapp.models.model.Movie
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