package com.octopus.moviesapp.ui.tv_show_details.uistate

import java.util.*

data class DetailsUiState(
    val id: Int = 0,
    val title: String = "",
    val coverImageUrl: String = "",
    val posterImageUrl: String = "",
    val voteCount: Int = 0,
    val voteAverage: Float = 0f,
    val episodesNumber: Int = 0,
    val seasonsNumber: Int = 0,
    val originalLanguage: String = "",
    val started: Date = Date(),
    val tagline: String = "",
    val overview: String = "",
    val status: String = "",
    val genres: List<GenresUiState> = emptyList(),
    val seasons: List<SeasonUiState> = emptyList(),
)
