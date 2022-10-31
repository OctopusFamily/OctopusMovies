package com.octopus.moviesapp.ui.movie_details.mapper

import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.ui.movie_details.uistate.MovieDetailsUiState
import com.octopus.moviesapp.ui.tv_show_details.mappers.GenresUiStateMapper
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class MovieDetailsUiStateMapper @Inject constructor(
    private val genresUiStateMapper: GenresUiStateMapper,
) {
    fun map(input: MovieDetails): MovieDetailsUiState {
        val genresList = input.genres

        return MovieDetailsUiState(
            id = input.id ?: 0,
            title = input.title ?: "",
            coverImageUrl = buildImageUrl(input.coverImageUrl),
            posterImageUrl = buildImageUrl(input.posterImageUrl),
            voteCount = input.voteCount ?: 0,
            voteAverage = input.voteAverage ?: 0f,
            originalLanguage = input.originalLanguage ?: "",
            tagline = input.tagline ?: "",
            overview = input.overview ?: "",
            genres = genresUiStateMapper.map(Pair(genresList, GenresType.MOVIE)),
            runtime = input.runtime,
            started = input.releaseDate,
        )
    }
}