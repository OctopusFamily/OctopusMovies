package com.octopus.moviesapp.ui.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.data.repository.MainRepository
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel(), TVShowsClicksListener {

    private val _tvShowsListState = MutableLiveData<UiState<List<TVShow>>>(UiState.Loading)
    val tvShowsListState: LiveData<UiState<List<TVShow>>> get() = _tvShowsListState

    private var currentTVShowsCategory = TVShowsCategory.POPULAR

    private val _navigateToTVShowDetails = MutableLiveData<Event<Int>>()
    val navigateToTVShowDetails: LiveData<Event<Int>> = _navigateToTVShowDetails

    init {
        getTVShowsByCategory(currentTVShowsCategory)
    }

    private fun getTVShowsByCategory(category: TVShowsCategory) {
        viewModelScope.launch {
            wrapResponse { repository.getTVShowsByCategory(category, 1) }.collectLatest {
                _tvShowsListState.postValue(it)
            }
        }
    }

    override fun onTVShowClick(tvShow: TVShow) {
        _navigateToTVShowDetails.postEvent(tvShow.id)
    }

    fun onChipClick(tvShowsCategory: TVShowsCategory) {
        if (tvShowsCategory != currentTVShowsCategory) {
            getTVShowsByCategory(tvShowsCategory)
            currentTVShowsCategory = tvShowsCategory
        }
    }

    fun tryLoadTVShowsAgain() {
        getTVShowsByCategory(currentTVShowsCategory)
    }
}