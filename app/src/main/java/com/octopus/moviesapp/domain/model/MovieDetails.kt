package com.octopus.moviesapp.domain.model

import java.util.*

data class MovieDetails(
    val id: Int,
    val title: String,
    val coverImageUrl: String,
    val posterImageUrl: String,
    val voteCount: Int,
    val voteAverage: Float,
    val releaseDate: Date,
    val runtime: Int,
    val originalLanguage: String,
    val tagline: String,
    val overview: String,
    val genres: List<Genre>,
)