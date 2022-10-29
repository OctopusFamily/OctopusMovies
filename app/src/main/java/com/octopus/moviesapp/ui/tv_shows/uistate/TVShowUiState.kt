package com.octopus.moviesapp.ui.tv_shows.uistate

import java.util.*

data class TVShowUiState(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rating: Float,
    val released: Date,
)