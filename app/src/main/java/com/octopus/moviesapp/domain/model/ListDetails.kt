package com.octopus.moviesapp.domain.model

data class ListDetails (
    val id: Int,
    val title: String,
    val releaseDate: String,
    val voteAverage: Double,
    val posterPath: String,
)