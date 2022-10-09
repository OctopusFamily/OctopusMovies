package com.octopus.moviesapp.domain.model

import java.util.*

data class TvShowDetails(
    val id: Int,
    val title: String,
    val coverImageUrl: String,
    val posterImageUrl: String,
    val voteCount: Int,
    val voteAverage: Float,
    val trailerUrl: String,
    val numberOfEpisode: Int,
    val numberOfSeason: Int,
    val started: Date,
    val runtime: Int,
    val originalLanguage: String,
    val tagline: String,
    val overview: String,
    val genres: List<Genre>,
    val cast: List<Person>,
    val seasons: List<Season>,
)
