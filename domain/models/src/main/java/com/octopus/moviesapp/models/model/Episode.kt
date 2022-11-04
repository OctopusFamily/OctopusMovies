package com.octopus.moviesapp.models.model


data class Episode(
    val name :String,
    val episodeNumber: Int,
    val date: String,
    val voteAverage: Float,
    val voteCount: Int,
    val overview: String,
    val trailerUrl: String,
    val crew : List<Cast>,
)
