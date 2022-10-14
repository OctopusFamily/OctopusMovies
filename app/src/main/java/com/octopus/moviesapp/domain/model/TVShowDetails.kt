package com.octopus.moviesapp.domain.model

import java.util.*

data class TVShowDetails(
    val id: Int,
    val title: String,
    val coverImageUrl: String,
    val posterImageUrl: String,
    val voteCount: Int,
    val voteAverage: Float,
    val episodesNumber: Int,
    val seasonsNumber: Int,
    val started: Date,
    val originalLanguage: String,
    val tagline: String,
    val overview: String,
    val status: String,
    val genres: List<Genre>,
    val seasons: List<Season>,
)
