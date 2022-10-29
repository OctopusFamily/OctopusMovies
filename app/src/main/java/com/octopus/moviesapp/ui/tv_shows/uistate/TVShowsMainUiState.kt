package com.octopus.moviesapp.ui.tv_shows.uistate

import androidx.paging.PagingData
import com.octopus.moviesapp.domain.types.TVShowsCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TVShowsMainUiState(
    val tvShowsUiState: Flow<PagingData<TVShowUiState>> = emptyFlow(),
    val selectedChip: Pair<Int, TVShowsCategory> = Pair(0, TVShowsCategory.POPULAR),
)