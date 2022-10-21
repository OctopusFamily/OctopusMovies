package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertStringToDate
import javax.inject.Inject

class MoviesMapper @Inject constructor() : Mapper<List<MovieDTO>, List<Movie>>() {
    override fun map(input: List<MovieDTO>): List<Movie> {
        return input.map {
            Movie(
                id = it.id ?: 0,
                title = it.originalTitle ?: "",
                posterImageUrl = buildImageUrl(it.posterPath),
                voteAverage = it.voteAverage ?: 0f,
                releaseDate = convertStringToDate(it.releaseDate),
            )
        }
    }
}