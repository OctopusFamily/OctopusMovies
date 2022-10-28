package com.octopus.moviesapp.ui.tv_show_details.tvShowCastState


data class TvShowCastMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess : Boolean = false,
    val tVShowCastUiState: List<TVShowCastUiState> = emptyList()
)
