package com.octopus.moviesapp.ui.home

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
import com.octopus.moviesapp.util.Constants
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

    private val _navigateToMovieDetails = MutableLiveData<Event<Int>>()
    val navigateToMovieDetails: LiveData<Event<Int>> get() = _navigateToMovieDetails

    private val _navigateToTVShowDetails = MutableLiveData<Event<Int>>()
    val navigateToTVShowDetails: LiveData<Event<Int>> get() = _navigateToTVShowDetails

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val trendingMovies = getTrendingMovies()
                val recommendedMovies = getRecommendedMovies()
                val trendingPeople = getTrendingPeople()
                val recommendedTVShows = getRecommendedTVShows()
                val trendingTVShows = getTrendingTVShows()
                _homeMainUiState.update { it.copy(
                    isSuccess = true,
                    isLoading = false,
                    isError = false,
                    trendingMoviesUiState = trendingMovies,
                    trendingTVShowsUiState = trendingTVShows,
                    trendingPeopleUiState = trendingPeople,
                    moviesItemUiState = recommendedMovies,
                    tvShowsItemUiState = recommendedTVShows,
                ) }
            } catch (e: Exception) {
                _homeMainUiState.update { it.copy(isError = true, isLoading = false, isSuccess = false) }
            }
        }
    }

    fun onSearchClick() {
        _navigateToSearch.postEvent(true)
    }

    fun tryToFetchDataAgain() {
        fetchData()
    }

    private suspend fun getTrendingMovies(): List<TrendingUiState> {
        return getTrendingMediaUseCase(MediaType.MOVIE).map { it.asTrendingUiState() }
    }

    private suspend fun getTrendingTVShows(): List<TrendingUiState> {
        return getTrendingMediaUseCase(MediaType.TV).map { it.asTrendingUiState() }
    }

    private suspend fun getTrendingPeople(): List<TrendingUiState> {
        return getTrendingMediaUseCase(MediaType.PERSON).map { it.asTrendingUiState() }
    }

    private suspend fun getRecommendedMovies(): List<MovieUiState> {
        return getMoviesByCategoryUseCase(MoviesCategory.POPULAR, Constants.FIRST_PAGE).map { it.asMovieUiState() }
    }

    private suspend fun getRecommendedTVShows(): List<TVShowUiState> {
        return getTVShowsByCategoryUseCase(TVShowsCategory.POPULAR, Constants.FIRST_PAGE).map { it.asTVShowUiState() }
    }

    override fun onMovieClick(movieId: Int) {
        _navigateToMovieDetails.postEvent(movieId)
    }

    override fun onTVShowClick(tvShowId: Int) {
        _navigateToTVShowDetails.postEvent(tvShowId)
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