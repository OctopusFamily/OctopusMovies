package com.octopus.moviesapp.domain.model

import com.octopus.moviesapp.android.local.types.MediaType

data class Trending(
    val id: Int,
    val imageUrl: String,
    val mediaType: MediaType,
)
