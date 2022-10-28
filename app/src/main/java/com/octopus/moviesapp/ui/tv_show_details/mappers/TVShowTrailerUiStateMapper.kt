package com.octopus.moviesapp.ui.tv_show_details.mappers

import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowTrailerState.TVShowTrailerUiState
import javax.inject.Inject

class TVShowTrailerUiStateMapper @Inject constructor() {
     fun map(input: Trailer): TVShowTrailerUiState {
        return TVShowTrailerUiState(
        url = input.url
        )
    }
}