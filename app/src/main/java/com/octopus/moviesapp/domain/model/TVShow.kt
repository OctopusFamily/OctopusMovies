package com.octopus.moviesapp.domain.model

import java.util.*

data class TVShow(
    val posterImage: String,
    val coverImage: String,
    val started: Date,
    val name: String,
    val status: String,
    val overview: String,
    val voteAverage: Float,
    val voteCount: Int,
    val tagline: String,
    val numberOfEpisode: Int,
    val numberOfSeason: Int,
    val trailerUrl: String,
    val languages: String,
    val genres: List<Genre>,
    val season: List<Season>,
    val createdBy: List<Person>,
)