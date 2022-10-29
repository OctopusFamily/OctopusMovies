package com.octopus.moviesapp.ui.tv_show_details.uistate


data class TvShowDetailsMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess : Boolean = false,
    val Info: TVShowDetailsUiState = TVShowDetailsUiState(),
    val trailer: TVShowTrailerUiState = TVShowTrailerUiState(),
    val cast: List<CastUiState> = emptyList()
    )
