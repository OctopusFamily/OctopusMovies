package com.octopus.moviesapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val coverImageUrl: String?,
    val posterImageUrl: String?,
    val voteCount: Int,
    val voteAverage: Float,
    val trailerUrl: String,
    val release_date: String,
    val runtime: Int,
    val originalLanguage: String,
    val genres: List<Genre>,
    val tagline: String,
    val overview: String,
    val cast: List<Person>,
)