package com.octopus.moviesapp.ui.movies_genre.uistate

import androidx.paging.PagingData
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviesGenreMainUiState(
    val moviesUiState: Flow<PagingData<MovieUiState>> = emptyFlow()

)
