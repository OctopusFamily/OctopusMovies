package com.octopus.moviesapp.ui.movie_details.uistate

import com.octopus.moviesapp.ui.tv_show_details.uistate.GenresUiState
import java.util.*

data class MovieDetailsUiState(
    val id: Int = 0,
    val title: String = "",
    val coverImageUrl: String = "",
    val posterImageUrl: String = "",
    val voteCount: Int = 0,
    val voteAverage: Float = 0f,
    val originalLanguage: String = "",
    val started: Date = Date(),
    val tagline: String = "",
    val overview: String = "",
    val status: String = "",
    val runtime: Int =0,
    val releaseDate : Date = Date(),
    val genres: List<GenresUiState> = emptyList(),
)
