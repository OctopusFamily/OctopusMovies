package com.octopus.moviesapp.ui.genre_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.use_case.GetGenreMoviesPagingSourceUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState
import com.octopus.moviesapp.ui.genre_movies.uistate.GenreMoviesMainUiState
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
class GenreMoviesViewModel @Inject constructor(
    private val getMoviesPagingSource: GetGenreMoviesPagingSourceUseCase,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel(), MoviesClicksListener {

    private val _genreMovieState = MutableStateFlow(GenreMoviesMainUiState())
    val genreMovieState: StateFlow<GenreMoviesMainUiState> get() = _genreMovieState

    private val _navigateToMovieDetails = MutableLiveData<Event<Int>>()
    val navigateToMovieDetails: LiveData<Event<Int>> = _navigateToMovieDetails

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val args = GenreMoviesFragmentArgs.fromSavedStateHandle(saveStateHandle)

    init {
        fetchGenreMovies()
    }

    private fun fetchGenreMovies() {
        val moviesUiStateFlow = Pager(
            PagingConfig(
                Constants.ITEMS_PER_PAGE,
                enablePlaceholders = true
            )
        ) { getMoviesPagingSource(args.genreId) }.flow.cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { movie -> movie.asMovieUiState() }
            }
        _genreMovieState.update {
            it.copy(
                moviesUiState = moviesUiStateFlow,
                genreName = args.genreName
            )
        }
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
