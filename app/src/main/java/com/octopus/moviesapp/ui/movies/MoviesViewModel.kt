package com.octopus.moviesapp.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.repository.MainRepositoryImpl
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MainRepositoryImpl
) : BaseViewModel(), MoviesClicksListener {

    private val _moviesListState = MutableLiveData<UiState<PagingData<Movie>>>(UiState.Loading)

    val moviesListState: LiveData<UiState<PagingData<Movie>>>
        get() = _moviesListState

    private var currentMoviesCategory = MoviesCategory.POPULAR

    private val _navigateToMoviesDetails = MutableLiveData<Event<Int?>>()
    val navigateToMoviesDetails: LiveData<Event<Int?>> = _navigateToMoviesDetails


    init {
        getMoviesByCategory(currentMoviesCategory)
    }

    private fun getMoviesByCategory(category: MoviesCategory) {
        viewModelScope.launch {
            wrapResponse {
                Log.v("tests","start")
                repository.getMoviesByCategory(category, 1).collectLatest {
                Log.v("tests","get ${it.toString()}")
//                    _moviesListState.postValue(it)
                }
            }
        }
    }

    fun getMovies(): Flow<PagingData<Movie>> {
        return repository.getMoviesByCategory(currentMoviesCategory, 1).map { it ->
            it
        }.cachedIn(viewModelScope)
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