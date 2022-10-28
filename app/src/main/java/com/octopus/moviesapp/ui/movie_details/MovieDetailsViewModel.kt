package com.octopus.moviesapp.ui.movie_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.local.datastore.DataStorePref
import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.util.*
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val connectionTracker: ConnectionTracker,
    private val dataStorePref: DataStorePref,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel(), NestedGenresListener, NestedCastListener {

    private val _movieDetailsState = MutableLiveData<UiState<MovieDetails>>(UiState.Loading)
    val movieDetailsState: LiveData<UiState<MovieDetails>> get() = _movieDetailsState

    private val _movieTrailerState = MutableLiveData<UiState<Trailer>>(UiState.Loading)
    val movieTrailerState: LiveData<UiState<Trailer>> get() = _movieTrailerState

    private val _movieCastState = MutableLiveData<UiState<List<Cast>>>(UiState.Loading)
    val movieCastState: LiveData<UiState<List<Cast>>> get() = _movieCastState

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> get() = _movieDetails

    private val _movieTrailer = MutableLiveData<Trailer>()

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _playTrailer = MutableLiveData<Event<String>>()
    val playTrailer: LiveData<Event<String>> get() = _playTrailer

    private val _saveToWatchList = MutableLiveData<Event<Int>>()
    val saveToWatchList: LiveData<Event<Int>> get() = _saveToWatchList


    private val _showRatingDialog = MutableLiveData<Event<Int>>()
    val showRatingDialog: LiveData<Event<Int>> get() = _showRatingDialog

    private val _navigateToMoviesGenre = MutableLiveData<Event<Genre>>()
    val navigateToMoviesGenre: LiveData<Event<Genre>> get() = _navigateToMoviesGenre

    private val _navigateToPersonDetails = MutableLiveData<Event<Int>>()
    val navigateToPersonDetails: LiveData<Event<Int>> get() = _navigateToPersonDetails

    private val args = MovieDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    init {
        loadMovieDetails(args.movieId)
        getRatedMovies()
    }

    private var movieID = 0
    private fun loadMovieDetails(movieId: Int) {
        movieID = movieId

        viewModelScope.launch {
            if (connectionTracker.isInternetConnectionAvailable()) {
                getMovieDetails()
            } else {
                _movieDetailsState.postValue(UiState.Error(Constants.ERROR_INTERNET))
            }
        }

    }

    private fun getMovieDetails() {
        Log.d("movieId :",args.movieId.toString())
        getMovieDetails(args.movieId)
        getMovieCast(args.movieId)
        getMovieTrailer(args.movieId)
    }

    fun tryLoadMovieDetailsAgain() {
        loadMovieDetails(args.movieId)
    }

    fun onLoadMovieDetailsSuccess(movieDetails: MovieDetails) {
        _movieDetails.postValue(movieDetails)
    }

    fun onLoadTrailerSuccess(movieTrailer: Trailer) {
        _movieTrailer.postValue(movieTrailer)
    }

    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    fun onSaveToWatchListClick() {
        _saveToWatchList.postEvent(0)
    }

    fun onPlayTrailerClick() {
        _movieTrailer.value?.let { trailer ->
            _playTrailer.postEvent(trailer.url)
        }
    }

    fun onShowRatingDialog(){
        _showRatingDialog.postEvent(args.movieId)
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            wrapResponse { moviesRepository.getMovieDetailsById(movieId) }.collectLatest {
                _movieDetailsState.postValue(it)
            }
        }
    }

    private fun getRatedMovies() {
        viewModelScope.launch {
            dataStorePref.readString(Constants.SESSION_ID_KEY).collect { sessionId ->
                sessionId?.let {
                    wrapResponse { moviesRepository.getRatedMovies(0, it) }.collectLatest {
                        Log.i("getRatedList", it.toData().toString())
                    }
                }
            }
        }
    }

    private fun getMovieTrailer(movieId: Int) {
        viewModelScope.launch {
            wrapResponse { moviesRepository.getMovieTrailerById(movieId) }.collectLatest {
                _movieTrailerState.postValue(it)
            }
        }
    }

    private fun getMovieCast(movieId: Int) {
        viewModelScope.launch {
            wrapResponse { moviesRepository.getMovieCastById(movieId) }.collectLatest {
                _movieCastState.postValue(it)
            }
        }
    }

    override fun onGenreClick(genre: Genre) {
        _navigateToMoviesGenre.postEvent(genre)
    }

    override fun onCastClick(castId: Int) {
        _navigateToPersonDetails.postEvent(castId)
    }

//    override fun onPersonClick(PersonId: Int) {
//        _navigateToPersonDetails.postEvent(PersonId)
//    }
}