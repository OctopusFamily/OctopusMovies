package com.octopus.moviesapp.ui.home.uistate

import com.octopus.moviesapp.ui.movies.uistate.MovieUiState
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState

data class HomeMainUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val trendingMoviesUiState: List<TrendingUiState> = emptyList(),
    val trendingTVShowsUiState: List<TrendingUiState> = emptyList(),
    val trendingPeopleUiState: List<TrendingUiState> = emptyList(),
    val tvShowsItemUiState: List<TVShowUiState> = emptyList(),
    val moviesItemUiState: List<MovieUiState> = emptyList(),
)