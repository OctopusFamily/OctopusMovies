package com.octopus.moviesapp.domain.model

import java.util.*

data class SearchResult(
    val id: Int,
    val title: String,
    val posterImageUrl: String,
    val voteAverage: Float,
    val releaseDate: Date,
    val mediaType: String,
    val originalLanguage: String?,
    val overview: String?,
)
