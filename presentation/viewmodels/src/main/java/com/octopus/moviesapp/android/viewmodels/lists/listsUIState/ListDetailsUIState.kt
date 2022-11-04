package com.octopus.moviesapp.ui.lists.listsUIState

data class ListDetailsUIState(
    val id: Int = 0,
    val title: String = "",
    val releaseDate: String = "",
    val voteAverage: Double = 0.0,
    val posterPath: String = "",
)
