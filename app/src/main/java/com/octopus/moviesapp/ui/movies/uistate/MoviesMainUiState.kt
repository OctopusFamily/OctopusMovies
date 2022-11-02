package com.octopus.moviesapp.ui.movies.uistate

import androidx.paging.PagingData
import com.octopus.moviesapp.domain.types.MoviesCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviesMainUiState(
    val moviesUiState: Flow<PagingData<MovieUiState>> = emptyFlow(),
    val selectedChip: Pair<Int, MoviesCategory> = Pair(0, MoviesCategory.POPULAR)
)
