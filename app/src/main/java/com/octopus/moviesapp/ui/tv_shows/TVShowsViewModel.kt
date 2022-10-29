package com.octopus.moviesapp.ui.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.types.TVShowsCategory
import com.octopus.moviesapp.domain.use_case.GetTVShowsPagingSourceUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.search.ChipGroupClickListener
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowsMainUiState
import com.octopus.moviesapp.util.*
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val getTVShowsPagingSourceUseCase: GetTVShowsPagingSourceUseCase
    ) : BaseViewModel(), TVShowsClicksListener, ChipGroupClickListener {

    private val _tvShowsMainUiState = MutableStateFlow(TVShowsMainUiState())
    val tvShowsMainUiState: StateFlow<TVShowsMainUiState> get() = _tvShowsMainUiState

    private val _navigateToTVShowDetails = MutableLiveData<Event<Int>>()
    val navigateToTVShowDetails: LiveData<Event<Int>> = _navigateToTVShowDetails

    init {
        fetchTVShowsByCategory()
    }

    override fun onTVShowClick(tvShowId: Int) {
        _navigateToTVShowDetails.postEvent(tvShowId)
    }

    private fun fetchTVShowsByCategory() {
        val tvShowsUiStateFlow = Pager(PagingConfig(Constants.ITEMS_PER_PAGE, enablePlaceholders = true)) { getTVShowsPagingSourceUseCase(tvShowsMainUiState.value.selectedChip.second) }.flow.cachedIn(viewModelScope).map { pagingData ->
            pagingData.map { tvShow -> tvShow.asTVShowUiState() }
        }
        _tvShowsMainUiState.update { it.copy(tvShowsUiState = tvShowsUiStateFlow) }
    }

    override fun onChipSelected(selectedChipPosition: Int) {
        _tvShowsMainUiState.update { it.copy(selectedChip = Pair(selectedChipPosition, getSelectedTVShowCategory(selectedChipPosition))) }
        fetchTVShowsByCategory()
    }

    private fun getSelectedTVShowCategory(position: Int): TVShowsCategory {
        return when(position) {
            0 -> TVShowsCategory.POPULAR
            1 -> TVShowsCategory.ON_THE_AIR
            2 -> TVShowsCategory.AIRING_TODAY
            3 -> TVShowsCategory.TOP_RATED
            else -> TVShowsCategory.POPULAR
        }
    }

    private fun TVShow.asTVShowUiState(): TVShowUiState {
        return TVShowUiState(
            id = id,
            title = title,
            imageUrl = posterImageUrl,
            released = started,
            rating = voteAverage,
        )
    }
}