package com.octopus.moviesapp.android.ui.movies.uistate

import java.util.*

data class MovieUiState(
    val id: Int,
    val title: String,
    val released: Date,
    val rating: Float,
    val imageUrl: String
)
