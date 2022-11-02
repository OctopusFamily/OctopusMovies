package com.octopus.moviesapp.ui.genre_tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.use_case.GetGenreTVShowsPagingSourceUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState
import com.octopus.moviesapp.ui.genre_tv_shows.uistate.GenreTVShowsMainUiState
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GenreTVShowsViewModel @Inject constructor(
    private val getTVShowsPagingSource: GetGenreTVShowsPagingSourceUseCase,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel(), TVShowsClicksListener {

    private val _genreTvShowState = MutableStateFlow(GenreTVShowsMainUiState())
    val genreTvShowState: StateFlow<GenreTVShowsMainUiState> get() = _genreTvShowState

    private val _navigateToTVShowDetails = MutableLiveData<Event<Int>>()
    val navigateToTVShowDetails: LiveData<Event<Int>> = _navigateToTVShowDetails

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val args = GenreTVShowsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    init {
        fetchGenreTVShow()
    }

    private fun fetchGenreTVShow() {
        val tvShowsUiStateFlow = Pager(
            PagingConfig(
                Constants.ITEMS_PER_PAGE,
                enablePlaceholders = true
            )
        ) { getTVShowsPagingSource(args.genreId) }.flow.cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { tvShow -> tvShow.asTVShowUiState() }
            }
        _genreTvShowState.update {
            it.copy(
                tvShowsUiState = tvShowsUiStateFlow,
                genreName = args.genreName
            )
        }
    }

    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    override fun onTVShowClick(tvShowId: Int) {
        _navigateToTVShowDetails.postEvent(tvShowId)
    }

    private fun TVShow.asTVShowUiState(): TVShowUiState {
        return TVShowUiState(
            id = id,
            title = title,
            imageUrl = posterImageUrl,
            released = started,
            rating = voteAverage
        )
    }
}
