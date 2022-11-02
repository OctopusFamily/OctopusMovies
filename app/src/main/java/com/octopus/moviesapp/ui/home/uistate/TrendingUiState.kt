package com.octopus.moviesapp.ui.home.uistate

import com.octopus.moviesapp.domain.types.MediaType

data class TrendingUiState(
    val id: Int,
    val imageUrl: String,
    val mediaType: MediaType,
)