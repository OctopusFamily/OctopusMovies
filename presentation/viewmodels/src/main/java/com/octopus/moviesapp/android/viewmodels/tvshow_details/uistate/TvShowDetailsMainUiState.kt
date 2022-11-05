package com.octopus.moviesapp.android.viewmodels.tvshow_details.uistate


data class TvShowDetailsMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess : Boolean = false,
    val info: DetailsUiState = DetailsUiState(),
    val trailer: TrailerUiState = TrailerUiState(),
    val cast: List<CastUiState> = emptyList()
    )
