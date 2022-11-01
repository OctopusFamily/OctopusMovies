package com.octopus.moviesapp.ui.movie_details.uistate

import com.octopus.moviesapp.domain.types.GenresType

data class MovieDetailsGenresUiState(
    val id: Int,
    val name: String,
    val type: GenresType,
)
