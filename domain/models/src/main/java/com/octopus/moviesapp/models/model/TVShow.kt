package com.octopus.moviesapp.models.model

import java.util.*

data class TVShow(
    val id: Int,
    val title: String,
    val posterImageUrl: String,
    val voteAverage: Float,
    val started: Date,
)