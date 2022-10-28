package com.octopus.moviesapp.ui.movie_details.uistate

import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class MovieDetailsUiStateMapper @Inject constructor(){
    fun map(input: TVShowDetails): MovieDetailsUiState {
        return MovieDetailsUiState(
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
        )
    }
}