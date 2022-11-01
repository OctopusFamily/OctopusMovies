package com.octopus.moviesapp.ui.tv_show_details.mappers

import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.ui.tv_show_details.uistate.TrailerUiState
import javax.inject.Inject

class TVShowTrailerUiStateMapper @Inject constructor() {
     fun map(input: Trailer): TrailerUiState {
        return TrailerUiState(
        url = input.url
        )
    }
}