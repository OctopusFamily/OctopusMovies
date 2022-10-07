package com.octopus.moviesapp.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val coverImageUrl: String?,
    val posterImageUrl: String?,
    val voteCount: Int,
    val voteAverage: Float,
    val trailerUrl: String,
    val releaseDate: String,
    val runtime: Int,
    val originalLanguage: String,
    val tagline: String,
    val overview: String,
    val genres: List<Genre>,
    val cast: List<Person>,
)