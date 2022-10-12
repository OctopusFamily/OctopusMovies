package com.octopus.moviesapp.ui.movie_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.MainRepository
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MainRepository,
) : BaseViewModel() {

    fun loadMovieDetails(movieId: Int) {
        Log.d("MALT", "ID: $movieId")
    }

    private val _movieDetailsState = MutableLiveData<UiState<MovieDetails>>(UiState.Loading)
    val movieDetailsState: LiveData<UiState<MovieDetails>> get() = _movieDetailsState

    private val _movieTrailerState = MutableLiveData<UiState<Trailer>>(UiState.Loading)
    val movieTrailerState: LiveData<UiState<Trailer>> get() = _movieTrailerState

    private val _movieCastState = MutableLiveData<UiState<List<Cast>>>(UiState.Loading)
    val movieCastState: LiveData<UiState<List<Cast>>> get() = _movieCastState

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            wrapResponse { repository.getMovieDetailsById(movieId) }.collectLatest {
                _movieDetailsState.postValue(it)
            }
        }
    }

    private fun getMovieTrailer(movieId: Int) {
        viewModelScope.launch {
            wrapResponse { repository.getMovieTrailerById(movieId) }.collectLatest {
                _movieTrailerState.postValue(it)
            }
        }
    }

    private fun getMovieCast(movieId: Int) {
        viewModelScope.launch {
            wrapResponse { repository.getMovieCastById(movieId) }.collectLatest {
                _movieCastState.postValue(it)
            }
        }
    }
}