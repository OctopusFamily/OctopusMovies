package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertToDate
import java.util.*
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor(
    private val genresMapper: GenresMapper,
) : Mapper<MovieDTO, MovieDetails> {
    override fun map(input: MovieDTO): MovieDetails {
        val genresList = input.genres ?: emptyList()
        return MovieDetails(
            id = input.id ?: 0,
            title = input.originalTitle ?: "",
            coverImageUrl = input.backdropPath?.buildImageUrl() ?: "",
            posterImageUrl = input.posterPath?.buildImageUrl() ?: "",
            voteCount = input.voteCount ?: 0,
            voteAverage = input.voteAverage ?: 0f,
            releaseDate = input.releaseDate?.convertToDate() ?: Date(),
            runtime = input.runtime ?: 0,
            originalLanguage = input.originalLanguage ?: "",
            tagline = input.tagline ?: "",
            overview = input.overview ?: "",
            genres = genresMapper.map(Pair(genresList, GenresType.MOVIE)),
        )
    }
}