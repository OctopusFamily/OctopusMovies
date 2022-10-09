package com.octopus.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.repository.MainRepository
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel(), MoviesClicksListener {
    override fun onMovieClick(movieId: Int) {

    }

    private val _stateOfListMovies = MutableLiveData<UiState<List<Movie>>>()
    val stateOfListMovies: LiveData<UiState<List<Movie>>> get() = _stateOfListMovies

    private var currentMoviesCategory = MoviesCategory.POPULAR


    init {
        getMoviesByType(currentMoviesCategory)
    }

    fun onChipClick(moviesCategory: MoviesCategory) {
        if (moviesCategory != currentMoviesCategory) {
            getMoviesByType(moviesCategory)
            currentMoviesCategory = moviesCategory
        }
    }

    fun tryLoadMoviesAgain() {
        getMoviesByType(currentMoviesCategory)
    }

    private fun getMoviesByType(type: MoviesCategory) {
        viewModelScope.launch {
            wrapResponse { repository.getMoviesByType(type) }.collectLatest {
                _stateOfListMovies.postValue(it)
            }
        }
    }
}