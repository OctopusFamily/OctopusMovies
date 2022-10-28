package com.octopus.moviesapp.ui.tv_show_details.uistate.cast_uistate


data class CastMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess : Boolean = false,
    val tVShowCastUiState: List<CastUiState> = emptyList()
)
