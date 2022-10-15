package com.octopus.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.types.MoviesCategory
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.data.repository.MoviesRepository
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : BaseViewModel(), MoviesClicksListener {

    private val _moviesListState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val moviesListState: LiveData<UiState<List<Movie>>> get() = _moviesListState

    var currentMoviesCategory = MoviesCategory.POPULAR

    private val _navigateToMovieDetails = MutableLiveData<Event<Int>>()
    val navigateToMovieDetails: LiveData<Event<Int>> = _navigateToMovieDetails

    companion object GetCurrentMoviesCategory {
        fun callMe() = println("I'm called.")
    }

    init {
        getMoviesByCategory(currentMoviesCategory)
    }

    private fun getMoviesByCategory(category: MoviesCategory) {
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