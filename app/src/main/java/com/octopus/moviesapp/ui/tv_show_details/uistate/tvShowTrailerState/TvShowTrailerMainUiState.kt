package com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowTrailerState

import com.octopus.moviesapp.domain.model.TVShowDetails

data class TvShowTrailerMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess : Boolean = false,
    val tvShowTrailersUiState: TVShowTrailerUiState = TVShowTrailerUiState()
)
