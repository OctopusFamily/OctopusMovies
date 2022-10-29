package com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowDetailsState

import com.octopus.moviesapp.domain.types.GenresType

data class GenresUiState(
    val id: Int,
    val name: String,
    val type: GenresType,
)
