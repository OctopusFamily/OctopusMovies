package com.octopus.moviesapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.domain.types.MediaType
import com.octopus.moviesapp.domain.types.MoviesCategory
import com.octopus.moviesapp.domain.types.TVShowsCategory
import com.octopus.moviesapp.domain.use_case.FetchMoviesByCategoryUseCase
import com.octopus.moviesapp.domain.use_case.FetchTVShowsByCategoryUseCase
import com.octopus.moviesapp.domain.use_case.GetTrendingMediaUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.home.uistate.HomeMainUiState
import com.octopus.moviesapp.ui.home.uistate.TrendingUiState
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrendingMediaUseCase: GetTrendingMediaUseCase,
    private val getMoviesByCategoryUseCase: FetchMoviesByCategoryUseCase,
    private val getTVShowsByCategoryUseCase: FetchTVShowsByCategoryUseCase,
): BaseViewModel(), HomeClicksListener, MoviesClicksListener, TVShowsClicksListener {

    private val _homeMainUiState = MutableStateFlow(HomeMainUiState())
    val homeMainUiState: StateFlow<HomeMainUiState> get() = _homeMainUiState

    private val _navigateToSearch = MutableLiveData<Event<Boolean>>()
    val navigateToSearch: LiveData<Event<Boolean>> get() = _navigateToSearch

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            getTrendingMovies()
            getRecommendedMovies()
            getTrendingPeople()
            getRecommendedTVShows()
            getTrendingTVShows()
        }
    }

    fun onSearchClick() {
        _navigateToSearch.postEvent(true)
    }

    fun tryToFetchDataAgain() {
        fetchData()
    }

    private suspend fun getTrendingMovies() {
        try {
            val trendingMovies = getTrendingMediaUseCase(MediaType.MOVIE)
            _homeMainUiState.update { it.copy(isLoading = false, isSuccess = true, trendingMoviesUiState = trendingMovies.map { trending -> trending.asTrendingUiState() }) }
        } catch (e: Exception) {
            _homeMainUiState.update { it.copy(isLoading = false, isError = false) }
        }
    }

    private suspend fun getTrendingTVShows() {
        try {
            val trendingMovies = getTrendingMediaUseCase(MediaType.TV)
            _homeMainUiState.update { it.copy(isLoading = false, isSuccess = true, trendingTVShowsUiState = trendingMovies.map { trending -> trending.asTrendingUiState() }) }
        } catch (e: Exception) {
            _homeMainUiState.update { it.copy(isLoading = false, isError = false) }
        }
    }

    private suspend fun getTrendingPeople() {
        try {
            val trendingMovies = getTrendingMediaUseCase(MediaType.PERSON)
            _homeMainUiState.update { it.copy(isLoading = false, isSuccess = true, trendingPeopleUiState = trendingMovies.map { trending -> trending.asTrendingUiState() }) }
        } catch (e: Exception) {
            _homeMainUiState.update { it.copy(isLoading = false, isError = true) }
        }
    }

    private suspend fun getRecommendedMovies() {
        try {
            val movies = getMoviesByCategoryUseCase(MoviesCategory.POPULAR, 1)
            _homeMainUiState.update { it.copy(isLoading = false, isSuccess = true, moviesItemUiState = movies.map { movie -> movie.asMovieUiState() }) }
        } catch (e: Exception) {
            _homeMainUiState.update { it.copy(isLoading = false, isError = true) }
        }
    }

    private suspend fun getRecommendedTVShows() {
        try {
            val tvShows = getTVShowsByCategoryUseCase(TVShowsCategory.POPULAR, 1)
            _homeMainUiState.update { it.copy(isLoading = false, isSuccess = true, tvShowsItemUiState = tvShows.map { tvShow -> tvShow.asTVShowUiState() }) }
        } catch (e: Exception) {
            _homeMainUiState.update { it.copy(isLoading = false, isError = true) }
        }
    }

    override fun onMovieClick(movieId: Int) {
        Log.d("MALT", "MOVIE CLICKED WITH ID: $movieId")
    }

    override fun onTVShowClick(tvShowId: Int) {
        Log.d("MALT", "MOVIE CLICKED WITH ID: $tvShowId")
    }

    private fun Trending.asTrendingUiState(): TrendingUiState {
        return TrendingUiState(
            id = id,
            imageUrl = imageUrl,
            mediaType = mediaType
        )
    }

    private fun Movie.asMovieUiState(): MovieUiState {
        return MovieUiState(
            id = id,
            title = title,
            released = releaseDate,
            rating = voteAverage,
            imageUrl = posterImageUrl,
        )
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