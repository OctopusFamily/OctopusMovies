package com.octopus.moviesapp.ui.tv_show_details.mappers

import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowDetailsState.TVShowDetailsUiState
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class TVShowDetailsUiStateMapper @Inject constructor(
    private val seasonUiStateMapper: SeasonUiStateMapper,
    private val genresUiStateMapper: GenresUiStateMapper,
) {
    fun map(input: TVShowDetails): TVShowDetailsUiState {
        val genresList = input.genres
        val seasonsList = input.seasons
        return TVShowDetailsUiState(
            id = input.id ?: 0,
            title = input.title ?: "",
            coverImageUrl = buildImageUrl(input.coverImageUrl),
            posterImageUrl = buildImageUrl(input.posterImageUrl),
            voteCount = input.voteCount ?: 0,
            voteAverage = input.voteAverage ?: 0f,
            episodesNumber =  input.episodesNumber ?: 0,
            seasonsNumber = input.seasonsNumber ?: 0,
            started = input.started ,
            originalLanguage = input.originalLanguage ?: "",
            tagline = input.tagline ?: "",
            overview = input.overview?:  "",
            status = input.status ?: "",
            genres = genresUiStateMapper.map(Pair(genresList, GenresType.TV)),
            seasons = seasonUiStateMapper.map(seasonsList),

        )
    }
}