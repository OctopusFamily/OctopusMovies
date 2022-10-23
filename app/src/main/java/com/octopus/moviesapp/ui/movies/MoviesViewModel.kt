package com.octopus.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.local.database.entity.MovieEntity
import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.types.MoviesCategory
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.ConnectionTracker
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val connectionTracker: ConnectionTracker,
) : BaseViewModel(), MoviesClicksListener {

    private val _moviesListState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val moviesListState: LiveData<UiState<List<Movie>>> get() = _moviesListState

    private var currentMoviesCategory = MoviesCategory.POPULAR

    private val _navigateToMovieDetails = MutableLiveData<Event<Int>>()
    val navigateToMovieDetails: LiveData<Event<Int>> = _navigateToMovieDetails

    init {
        getMoviesByCategory(currentMoviesCategory)
        insertMovies()
    }

    private fun insertMovies() {
        viewModelScope.launch {
            moviesRepository.insertMovies(
                listOf(
                    MovieEntity(
                        id = 1,
                        title = "koko",
                        posterImageUrl = "posterImageUrl",
                        releaseDate = "releaseDate",
                        voteAverage = 1.0f
                    ),
                    MovieEntity(
                        id = 2,
                        title = "toto",
                        posterImageUrl = "posterImageUrl",
                        releaseDate = "releaseDate",
                        voteAverage = 1.0f
                    ),
                    MovieEntity(
                        id = 3,
                        title = "momo",
                        posterImageUrl = "posterImageUrl",
                        releaseDate = "releaseDate",
                        voteAverage = 1.0f
                    )
                )
            )
        }
    }

    private fun getMoviesByCategory(category: MoviesCategory) {
        viewModelScope.launch {
            if (connectionTracker.isInternetConnectionAvailable()) {
                loadMoviesByCategory(category)
            } else {
                _moviesListState.postValue(UiState.Error(Constants.ERROR_INTERNET))
            }
        }
    }

    private fun loadMoviesByCategory(category: MoviesCategory) {
        viewModelScope.launch {
            wrapResponse { moviesRepository.getMoviesByCategory(category, 1) }.collectLatest {
                _moviesListState.postValue(it)
            }
        }
    }

    override fun onMovieClick(movieId: Int) {
        _navigateToMovieDetails.postEvent(movieId)
    }

    fun onChipClick(moviesCategory: MoviesCategory) {
        if (moviesCategory != currentMoviesCategory) {
            getMoviesByCategory(moviesCategory)
            currentMoviesCategory = moviesCategory
        }
    }


    fun tryLoadMoviesAgain() {
        getMoviesByCategory(currentMoviesCategory)
    }
}