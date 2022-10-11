package com.octopus.moviesapp.domain.model

import java.util.*

data class Movie(
    val id: Int,
    val title: String,
    val posterImageUrl: String,
    val voteAverage: Float,
    val releaseDate: Date,
    )
