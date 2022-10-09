package com.octopus.moviesapp.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
    private val stateOfListMovies = MutableLiveData<UiState<List<Movie>>>()

    private fun collectListOfPopularMovies() {
        viewModelScope.launch {
            wrapResponse { repository.getPopularMovies() }.collectLatest {
                stateOfListMovies.postValue(it)
            }
        }
    }
}