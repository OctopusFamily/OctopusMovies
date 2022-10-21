package com.octopus.moviesapp.domain.mapper

import android.content.Context
import com.octopus.moviesapp.R
import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertStringToDate
import com.octopus.moviesapp.util.getTextOrPlaceholder
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor(
    @ApplicationContext private val context: Context,
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
            tagline = getTextOrPlaceholder(context, input.tagline, R.string.there_is_no_tagline),
            overview = getTextOrPlaceholder(context, input.overview, R.string.there_is_no_overview),
            genres = genresMapper.map(Pair(genresList, GenresType.MOVIE)),
        )
    }
}