package com.octopus.moviesapp.ui.tv_show_details.tvShowDetailsState

import com.octopus.moviesapp.domain.types.GenresType

data class TVShowDetailsGenresUiState(
    val id: Int,
    val name: String,
    val type: GenresType,
)
