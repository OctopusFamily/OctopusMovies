package com.octopus.moviesapp.models.model

import com.octopus.moviesapp.models.type.MediaType


data class Trending(
    val id: Int,
    val imageUrl: String,
    val mediaType: MediaType,
)
