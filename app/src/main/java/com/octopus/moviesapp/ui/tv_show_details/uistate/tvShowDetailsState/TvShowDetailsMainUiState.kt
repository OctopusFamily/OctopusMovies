package com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowDetailsState

import com.octopus.moviesapp.ui.tv_show_details.uistate.cast_uistate.CastUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowTrailerState.TVShowTrailerUiState


data class TvShowDetailsMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess : Boolean = false,
    val tvShowDetailsUiState: TVShowDetailsUiState = TVShowDetailsUiState(),
    val tVShowCastUiState: List<CastUiState> = emptyList(),
    val tvShowTrailersUiState: TVShowTrailerUiState = TVShowTrailerUiState()
    )
