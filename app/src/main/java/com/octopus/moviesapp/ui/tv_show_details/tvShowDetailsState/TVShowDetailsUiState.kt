package com.octopus.moviesapp.ui.tv_show_details.tvShowDetailsState

import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Season
import java.util.*

data class TVShowDetailsUiState(
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
    val genres: List<TVShowDetailsGenresUiState> = emptyList(),
    val seasons: List<TVShowSeasonUiState> = emptyList(),
)
