package com.octopus.moviesapp.ui.tv_show_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.MainRepository
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val repository: MainRepository,
) : BaseViewModel() {

    private val _tvShowDetailsState = MutableLiveData<UiState<TVShowDetails>>(UiState.Loading)
    val tvShowDetailsState: LiveData<UiState<TVShowDetails>> get() = _tvShowDetailsState
    private val _rateTvShow = MutableLiveData<Event<Int>>()
    val rateTvShow: LiveData<Event<Int>> get() = _rateTvShow

    private val _playTrailer = MutableLiveData<Event<String>>()
    val playTrailer: LiveData<Event<String>> get() = _playTrailer

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _saveToWatchList = MutableLiveData<Event<Int>>()
    val saveToWatchList: LiveData<Event<Int>> get() = _saveToWatchList

    private val _tvShowDetails = MutableLiveData<TVShowDetails>()
    val tvShowDetails: LiveData<TVShowDetails> get() = _tvShowDetails


    fun onPlayTrailerClick() {

    }

    fun onRateClick() {
        _rateTvShow.postEvent(0)
    }

    fun onSaveToWatchListClick() {
        _saveToWatchList.postEvent(0)
    }

    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    private var tvShowID = 0
    fun loadTVShowDetails(movieId: Int) {
        tvShowID = movieId
        getTVShowDetails(movieId)
    }

    fun onLoadTVShowDetailsSuccess(tvShowDetails: TVShowDetails) {
        _tvShowDetails.postValue(tvShowDetails)
    }

    fun tryLoadTVShowDetailsAgain() {
        loadTVShowDetails(tvShowID)
    }

    private fun getTVShowDetails(tvID: Int) {
        viewModelScope.launch {
            wrapResponse { repository.getTVShowDetailsById(tvID) }.collectLatest {
                _tvShowDetailsState.postValue(it)
            }
        }
    }

}