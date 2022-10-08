package com.octopus.moviesapp.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.repository.MainRepositoryImpl
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesViewModel : BaseViewModel() {

    private val repository = MainRepositoryImpl()

    private val stateOfListMovies = MutableLiveData<UiState<List<Movie>>>()

    private fun collectListOfPopularMovies() {
        viewModelScope.launch {
            wrapResponse { repository.getPopularMovies() }.collectLatest {
                stateOfListMovies.postValue(it)
            }
        }
    }
}