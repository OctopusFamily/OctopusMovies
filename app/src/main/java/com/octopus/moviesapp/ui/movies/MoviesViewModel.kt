package com.octopus.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.octopus.moviesapp.domain.types.MoviesCategory
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.data.repository.MoviesRepository
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val mapper: MoviesMapper,
) : BaseViewModel(), MoviesClicksListener {

    private val _moviesListState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val moviesListState: LiveData<UiState<List<Movie>>> get() = _moviesListState

    private var currentMoviesCategory = MoviesCategory.POPULAR

    private val _navigateToMovieDetails = MutableLiveData<Event<Int>>()
    val navigateToMovieDetails: LiveData<Event<Int>> = _navigateToMovieDetails

    init {
//        getMoviesByCategory(currentMoviesCategory)
    }

//    private fun getMoviesByCategory(category: MoviesCategory) {
//        viewModelScope.launch {
//            wrapResponse { moviesRepository.getMoviesByCategory(category, 1) }.collectLatest {
//                _moviesListState.postValue(it)
//            }
//        }
//    }

    override fun onMovieClick(movieId: Int) {
        _navigateToMovieDetails.postEvent(movieId)
    }

    fun onChipClick(moviesCategory: MoviesCategory) {
        if (moviesCategory != currentMoviesCategory) {
//            getMoviesByCategory(moviesCategory)
            currentMoviesCategory = moviesCategory
        }
    }

    fun tryLoadMoviesAgain() {
//        getMoviesByCategory(currentMoviesCategory)
    }

    val movies = Pager(
    config = PagingConfig(20, enablePlaceholders = true),
    pagingSourceFactory = { moviesRepository.getMovies(currentMoviesCategory) }
    ).flow.cachedIn(viewModelScope).map { pagingData ->
        pagingData.map {
            mapper.map(it)
        }
    }
}