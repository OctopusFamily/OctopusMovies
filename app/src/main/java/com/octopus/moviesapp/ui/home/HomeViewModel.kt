package com.octopus.moviesapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.home.HomeRepository
import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.domain.types.MediaType
import com.octopus.moviesapp.domain.types.MoviesCategory
import com.octopus.moviesapp.domain.types.TVShowsCategory
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
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
    private val moviesRepository: MoviesRepository,
    private val tvShowsRepository: TVShowsRepository,
): BaseViewModel(), HomeClicksListener, MoviesClicksListener, TVShowsClicksListener {

    private val _navigateToSearch = MutableLiveData<Event<Boolean>>()
    val navigateToSearch: LiveData<Event<Boolean>> get() = _navigateToSearch

    private val _trendingMoviesState = MutableLiveData<UiState<List<Trending>>>(UiState.Loading)
    val trendingMoviesState: LiveData<UiState<List<Trending>>> get() = _trendingMoviesState

    private val _trendingTVShowsState = MutableLiveData<UiState<List<Trending>>>(UiState.Loading)
    val trendingTVShowsState: LiveData<UiState<List<Trending>>> get() = _trendingTVShowsState

    private val _trendingPeopleState = MutableLiveData<UiState<List<Trending>>>(UiState.Loading)
    val trendingPeopleState: LiveData<UiState<List<Trending>>> get() = _trendingPeopleState

    private val _recommendedMovies = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val recommendedMovies: LiveData<UiState<List<Movie>>> get() = _recommendedMovies

    private val _recommendedTVShows = MutableLiveData<UiState<List<TVShow>>>(UiState.Loading)
    val recommendedTVShows: LiveData<UiState<List<TVShow>>> get() = _recommendedTVShows

    init {
        getTrendingMovies()
        getRecommendedMovies()
        getTrendingTVShows()
        getRecommendedTVShows()
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

    private fun getRecommendedMovies() {
        viewModelScope.launch {
            wrapResponse { moviesRepository.getMoviesByCategory(MoviesCategory.TOP_RATED, 1) }.collectLatest { uiState ->
                _recommendedMovies.postValue(uiState)
            }
        }
    }

    private fun getRecommendedTVShows() {
        viewModelScope.launch {
            wrapResponse {
                tvShowsRepository.getTVShowsByCategory(TVShowsCategory.TOP_RATED, 1) }.collectLatest { uiState ->
                _recommendedTVShows.postValue(uiState)
            }
        }
    }

    override fun onMovieClick(movieId: Int) {
        Log.d("MALT", "MOVIE CLICKED WITH ID: $movieId")
    }

    override fun onTVShowClick(tvShow: TVShow) {
        Log.d("MALT", "TV SHOW CLICKED WITH ID: ${tvShow.id}")
    }
}