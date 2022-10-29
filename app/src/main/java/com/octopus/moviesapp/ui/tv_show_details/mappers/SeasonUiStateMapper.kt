package com.octopus.moviesapp.ui.tv_show_details.mappers

import com.octopus.moviesapp.domain.model.Season
import com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowDetailsState.TVShowSeasonUiState
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class SeasonUiStateMapper  @Inject constructor() {
    fun map(input: List<Season>): List<TVShowSeasonUiState> {
        return input.map {
            TVShowSeasonUiState(
                id = it.id,
                seasonNumber = it.seasonNumber,
                imageUrl = buildImageUrl(it.imageUrl),
            )
        }
    }
}