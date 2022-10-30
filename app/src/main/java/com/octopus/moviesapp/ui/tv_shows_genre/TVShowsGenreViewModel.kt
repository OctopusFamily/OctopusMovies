package com.octopus.moviesapp.ui.tv_shows_genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.use_case.GetTVShowsByGenreIdPagingSourceUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState
import com.octopus.moviesapp.ui.tv_shows_genre.uistate.TVShowsGenreMainUiState
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TVShowsGenreViewModel @Inject constructor(
    private val getTVShowsByGenreIdPagingSourceUseCase: GetTVShowsByGenreIdPagingSourceUseCase
) : BaseViewModel(), TVShowsClicksListener {

    private val _tvShowGenreState = MutableStateFlow(TVShowsGenreMainUiState())
    val tvShowGenreState: StateFlow<TVShowsGenreMainUiState> get() = _tvShowGenreState

    private val _genreName = MutableLiveData("")
    val genreName: LiveData<String> get() = _genreName

    private val _navigateToTVShowDetails = MutableLiveData<Event<Int>>()
    val navigateToTVShowDetails: LiveData<Event<Int>> = _navigateToTVShowDetails

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private var genreID = 0
    fun loadTVShow(genreId: Int, genreName: String) {
        genreID = genreId
        _genreName.postValue(genreName)
        fetchTVShowByGenreId(genreID)
    }

    private fun fetchTVShowByGenreId(genreId: Int) {
        val tvShowsUiStateFlow = Pager(
            PagingConfig(
                Constants.ITEMS_PER_PAGE,
                enablePlaceholders = true
            )
        ) { getTVShowsByGenreIdPagingSourceUseCase(genreId) }.flow.cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { tvShow -> tvShow.asTVShowUiState() }
            }
        _tvShowGenreState.update { it.copy(tvShowsUiState = tvShowsUiStateFlow) }
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
