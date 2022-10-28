package com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowDetailsState

import com.octopus.moviesapp.domain.model.TVShowDetails

data class TvShowDetailsMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess : Boolean = false,
    val tvShowDetailsUiState: TVShowDetailsUiState = TVShowDetailsUiState()

)
