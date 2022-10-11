package com.octopus.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.model.Movie
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
class MoviesViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel(), MoviesClicksListener {


    private val _moviesListState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val moviesListState: LiveData<UiState<List<Movie>>> get() = _moviesListState

    private var currentMoviesCategory = MoviesCategory.POPULAR

    private val _navigateToMoviesDetails = MutableLiveData<Event<Int?>>()
    val navigateToMoviesDetails: LiveData<Event<Int?>> = _navigateToMoviesDetails

    init {
        getMoviesByCategory(currentMoviesCategory)
    }

    private fun getMoviesByCategory(category: MoviesCategory) {
        viewModelScope.launch {
            wrapResponse { repository.getMoviesByCategory(category, 1) }.collectLatest {
                _moviesListState.postValue(it)
            }
        }
    }

    override fun onMovieClick(movies: Movie) {
        _navigateToMoviesDetails.postEvent(movies.id)
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