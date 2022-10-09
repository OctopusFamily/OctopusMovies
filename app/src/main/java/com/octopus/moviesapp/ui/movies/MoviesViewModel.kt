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

    private val _moviesListState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val moviesListState: LiveData<UiState<List<Movie>>> get() = _moviesListState

    private var currentMoviesCategory = MoviesCategory.POPULAR
    init {
        getMoviesByCategory(currentMoviesCategory)
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

    private fun getMoviesByCategory(type: MoviesCategory) {
        viewModelScope.launch {
            wrapResponse { repository.getMoviesByCategory(type) }.collectLatest {
                _moviesListState.postValue(it)
            }
        }
    }
}