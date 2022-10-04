package com.octopus.moviesapp.domain.model

data class Episode(
    val name :String,
    val episodeNumber: Int,
    val voteAverage: Float,
    val date:String,
    val voteCount: Int,
    val overview: String,
    val crew : List<Person>,
    val trailerUrl: String,
)
