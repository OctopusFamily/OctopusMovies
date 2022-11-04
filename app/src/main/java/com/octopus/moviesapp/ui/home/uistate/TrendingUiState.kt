package com.octopus.moviesapp.ui.home.uistate

import com.octopus.moviesapp.android.local.types.MediaType

data class TrendingUiState(
    val id: Int,
    val imageUrl: String,
    val mediaType: MediaType,
)