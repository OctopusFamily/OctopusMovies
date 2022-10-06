package com.octopus.moviesapp.domain.model

data class Season(
    val name: String,
    val posterImageUrl: String,
    val coverImageUrl: String,
    val trailerUrl: String,
    val overview: String,
    val episodesCount: Int,
    val airDate: String,
    val episodes: List<Episode>,
)
