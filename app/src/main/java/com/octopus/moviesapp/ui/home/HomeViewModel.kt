package com.octopus.moviesapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.home.HomeRepository
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.domain.types.MediaType
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
): BaseViewModel(), HomeClicksListener {

    private val _navigateToSearch = MutableLiveData<Event<Boolean>>()
    val navigateToSearch: LiveData<Event<Boolean>> get() = _navigateToSearch

    private val _trendingMoviesState = MutableLiveData<UiState<List<Trending>>>(UiState.Loading)
    val trendingMoviesState: LiveData<UiState<List<Trending>>> get() = _trendingMoviesState

    private val _trendingTVShowsState = MutableLiveData<UiState<List<Trending>>>(UiState.Loading)
    val trendingTVShowsState: LiveData<UiState<List<Trending>>> get() = _trendingTVShowsState

    private val _trendingPeopleState = MutableLiveData<UiState<List<Trending>>>(UiState.Loading)
    val trendingPeopleState: LiveData<UiState<List<Trending>>> get() = _trendingPeopleState

    private val _isTextClicked = MutableLiveData(Event(false))
    val isTextClicked = _isTextClicked

    fun onTextClicked(){
        _isTextClicked.postValue(Event(true))
    }
    init {
        getTrendingMovies()
        getTrendingTVShows()
        getTrendingPeople()
    }

    fun onSearchClick() {
        _navigateToSearch.postEvent(true)
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            wrapResponse { homeRepository.getTrendingMedia(MediaType.MOVIE) }.collectLatest { uiState ->
                _trendingMoviesState.postValue(uiState)
            }
        }
    }

    private fun getTrendingTVShows() {
        viewModelScope.launch {
            wrapResponse { homeRepository.getTrendingMedia(MediaType.TV) }.collectLatest { uiState ->
                _trendingTVShowsState.postValue(uiState)
            }
        }
    }

    private fun getTrendingPeople() {
        viewModelScope.launch {
            wrapResponse { homeRepository.getTrendingMedia(MediaType.PERSON) }.collectLatest { uiState ->
                _trendingPeopleState.postValue(uiState)
            }
        }
    }
}