package com.octopus.moviesapp.domain.model

import com.octopus.moviesapp.domain.types.MediaType

data class Trending(
    val id: Int,
    val imageUrl: String,
    val mediaType: MediaType,
)
