package com.octopus.moviesapp.ui.movies_genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.use_case.GetMoviesByGenreIdPagingSourceUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState
import com.octopus.moviesapp.ui.movies_genre.uistate.MoviesGenreMainUiState
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MoviesGenreViewModel @Inject constructor(
    private val getMoviesByGenreIdPagingSourceUseCase: GetMoviesByGenreIdPagingSourceUseCase
) : BaseViewModel(), MoviesClicksListener {

    private val _movieGenreState = MutableStateFlow(MoviesGenreMainUiState())
    val movieGenreState: StateFlow<MoviesGenreMainUiState> get() = _movieGenreState

    private val _tvShowGenreState = MutableStateFlow(MoviesGenreMainUiState())
    val tvShowGenreState: StateFlow<MoviesGenreMainUiState> get() = _tvShowGenreState

    private val _genreName = MutableLiveData("")
    val genreName: LiveData<String> get() = _genreName

    private val _navigateToMovieDetails = MutableLiveData<Event<Int>>()
    val navigateToMovieDetails: LiveData<Event<Int>> = _navigateToMovieDetails

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private var genreID = 0
    fun loadMovies(genreId: Int, genreName: String) {
        genreID = genreId
        _genreName.postValue(genreName)
        fetchMoviesByGenreId(genreId)
    }

    private fun fetchMoviesByGenreId(genreId: Int) {
        val moviesUiStateFlow = Pager(
            PagingConfig(
                Constants.ITEMS_PER_PAGE,
                enablePlaceholders = true
            )
        ) { getMoviesByGenreIdPagingSourceUseCase(genreId) }.flow.cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { movie -> movie.asMovieUiState() }
            }
        _movieGenreState.update { it.copy(moviesUiState = moviesUiStateFlow) }
    }

    override fun onMovieClick(movieId: Int) {
        _navigateToMovieDetails.postEvent(movieId)
    }

    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    private fun Movie.asMovieUiState(): MovieUiState {
        return MovieUiState(
            id = id,
            title = title,
            imageUrl = posterImageUrl,
            released = releaseDate,
            rating = voteAverage
        )
    }
}
