package com.octopus.moviesapp.ui.genre_tv_shows.uistate

import androidx.paging.PagingData
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class GenreTVShowsMainUiState(
    val tvShowsUiState: Flow<PagingData<TVShowUiState>> = emptyFlow(),
    val genreName: String = ""

)
