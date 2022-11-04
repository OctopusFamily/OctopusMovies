package com.octopus.moviesapp.android.usecases.mapper

import com.octopus.moviesapp.android.remote.response.dto.MovieDTO
import com.octopus.moviesapp.models.model.MovieDetails
import com.octopus.moviesapp.repositories.repository.type.GenresType
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor(
    private val genresMapper: GenresMapper,
) : Mapper<MovieDTO, MovieDetails>() {
    override fun map(input: MovieDTO): MovieDetails {
        val genresList = input.genres ?: emptyList()
        return MovieDetails(
            id = input.id ?: 0,
            title = input.originalTitle ?: "",
            coverImageUrl = buildImageUrl(input.backdropPath),
            posterImageUrl = buildImageUrl(input.posterPath),
            voteCount = input.voteCount ?: 0,
            voteAverage = input.voteAverage ?: 0f,
            releaseDate = convertStringToDate(input.releaseDate),
            runtime = input.runtime ?: 0,
            originalLanguage = input.originalLanguage ?: "",
            tagline =input.tagline ?: "",
            overview =  input.overview ?: "",
            genres = genresMapper.map(Pair(genresList, GenresType.MOVIE)),
        )
    }
}