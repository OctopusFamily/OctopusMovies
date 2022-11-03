package com.octopus.moviesapp.ui.genres.uistate

import com.octopus.moviesapp.android.local.types.GenresType

data class GenresUiState(
    val id: Int,
    val name: String,
    val type: GenresType,
)