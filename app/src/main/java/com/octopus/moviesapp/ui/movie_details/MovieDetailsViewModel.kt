package com.octopus.moviesapp.ui.movie_details

import com.octopus.moviesapp.data.repository.MainRepository
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
    private val repository: MainRepository
) : BaseViewModel(), MovieDetailsClicksListener {

    private val _movieDetailsState = MutableLiveData<UiState<MovieDetails>>(UiState.Loading)
    val movieDetailsState: LiveData<UiState<MovieDetails>> get() = _movieDetailsState

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> get() = _movieDetails

    private val _backPressed = MutableLiveData(false)
    val backPressed get() = _backPressed

    private val _saveMovie = MutableLiveData(false)
    val saveMovie get() = _saveMovie

    private val _playTrailer = MutableLiveData(false)
    val playTrailer get() = _playTrailer

    private val _rateMovie = MutableLiveData(false)
    val rateMovie get() = _rateMovie

    override fun onBackPressed() {
        _backPressed.postValue(true)
    }

    override fun onSavePressed() {
        _saveMovie.postValue(true)
    }

    override fun onWatchTrailerPressed() {
        _playTrailer.postValue(true)
    }

    override fun onRatePressed() {
        _rateMovie.postValue(true)
    }
}