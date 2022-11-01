package com.octopus.moviesapp.ui.genre_movies.uistate

import androidx.paging.PagingData
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class GenreMoviesMainUiState(
    val moviesUiState: Flow<PagingData<MovieUiState>> = emptyFlow(),
    val genreName: String = ""

)
