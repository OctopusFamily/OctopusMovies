package com.octopus.moviesapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterImageUrl: String,
    val voteAverage: Float,
    val releaseDate: String,

    )
