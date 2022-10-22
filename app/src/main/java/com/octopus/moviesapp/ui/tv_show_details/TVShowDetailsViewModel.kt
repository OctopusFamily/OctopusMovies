package com.octopus.moviesapp.ui.tv_show_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Season
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.ui.nested.NestedSeasonsListener
import com.octopus.moviesapp.util.ConnectionTracker
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
    private val connectionTracker: ConnectionTracker,
    saveStateHandle: SavedStateHandle,
    ) : BaseViewModel(), NestedGenresListener, NestedCastListener, NestedSeasonsListener {

    private val _tvShowDetailsState = MutableLiveData<UiState<TVShowDetails>>(UiState.Loading)
    val tvShowDetailsState: LiveData<UiState<TVShowDetails>> get() = _tvShowDetailsState

    private val _tvTrailerState = MutableLiveData<UiState<Trailer>>(UiState.Loading)
    val tvTrailerState: LiveData<UiState<Trailer>> get() = _tvTrailerState

    private val _rateTvShow = MutableLiveData<Event<Int>>()
    val rateTvShow: LiveData<Event<Int>> get() = _rateTvShow

    private val _playTrailer = MutableLiveData<Event<String>>()
    val playTrailer: LiveData<Event<String>> get() = _playTrailer

    private val _tvTrailer = MutableLiveData<Trailer>()

    private val _tvShowCastState = MutableLiveData<UiState<List<Cast>>>(UiState.Loading)
    val tvShowCastState: LiveData<UiState<List<Cast>>> get() = _tvShowCastState

    private val _tvShowSeasonsState = MutableLiveData<UiState<List<Season>>>(UiState.Loading)
    val tvShowSeasonsState: LiveData<UiState<List<Season>>> get() = _tvShowSeasonsState

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _tvShowDetails = MutableLiveData<TVShowDetails>()
    val tvShowDetails: LiveData<TVShowDetails> get() = _tvShowDetails

    private val _navigateToTVShowsGenre = MutableLiveData<Event<Genre>>()
    val navigateToTVShowsGenre: LiveData<Event<Genre>> get() = _navigateToTVShowsGenre

    private val _navigateToPersonDetails = MutableLiveData<Event<Int>>()
    val navigateToPersonDetails: LiveData<Event<Int>> get() = _navigateToPersonDetails

    private val args = TVShowDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    init {
        loadTVShowDetails(args.tvShowId)
    }

    private var tvShowID = 0
    private fun loadTVShowDetails(tvShowId: Int) {
        tvShowID = tvShowId

        viewModelScope.launch {
            if (connectionTracker.isInternetConnectionAvailable()) {
                getTVShowDetailsInfo()
            } else {
                _tvShowDetailsState.postValue(UiState.Error(Constants.ERROR_INTERNET))
            }
        }

    }

    private fun getTVShowDetailsInfo() {
        getTVShowDetails(args.tvShowId)
        getTVShowCast(args.tvShowId)
        getTVTrailer(args.tvShowId)
    }


    private fun getTVTrailer(tvShowId: Int) {
        viewModelScope.launch {
            wrapResponse { tvShowsRepository.getTVShowsTrailersById(tvShowId) }.collectLatest {
                _tvTrailerState.postValue(it)
            }
        }
    }

    fun onLoadTrailerSuccess(tvTrailer: Trailer) {
        _tvTrailer.postValue(tvTrailer)
    }

    fun onPlayTrailerClick() {
        _tvTrailer.value?.let { trailer ->
            _playTrailer.postEvent(trailer.url)
        }
    }

    fun onRateClick() {
        _rateTvShow.postEvent(0)
    }

    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }


    fun onLoadTVShowDetailsSuccess(tvShowDetails: TVShowDetails) {
        _tvShowDetails.postValue(tvShowDetails)
    }

    fun tryLoadTVShowDetailsAgain() {
        loadTVShowDetails(args.tvShowId)
    }

    private fun getTVShowDetails(tvID: Int) {
        viewModelScope.launch {
            wrapResponse { tvShowsRepository.getTVShowDetailsById(tvID) }.collectLatest {
                _tvShowDetailsState.postValue(it)
            }
        }
    }



    private fun getTVShowCast(tvShowId: Int) {
        viewModelScope.launch {
            wrapResponse { tvShowsRepository.getTVShowCastById(tvShowId) }.collectLatest {
                _tvShowCastState.postValue(it)
                Log.i("wsh", "$it")
            }
        }
    }

    override fun onGenreClick(genre: Genre) {
        _navigateToTVShowsGenre.postEvent(genre)
    }

    override fun onCastClick(castId: Int) {
        _navigateToPersonDetails.postEvent(castId)
    }
}