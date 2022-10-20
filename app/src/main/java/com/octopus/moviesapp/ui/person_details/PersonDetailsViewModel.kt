package com.octopus.moviesapp.ui.person_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.PersonRepository
import com.octopus.moviesapp.domain.model.*
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.ConnectionTracker
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val connectionTracker: ConnectionTracker,
) : BaseViewModel() {

    private val _personDetailsState = MutableLiveData<UiState<PersonDetails>>(UiState.Loading)
    val personDetailsState: LiveData<UiState<PersonDetails>> get() = _personDetailsState

    private val _personMoviesState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val personMoviesState: LiveData<UiState<List<Movie>>> get() = _personMoviesState

    private val _personTVShowsState = MutableLiveData<UiState<List<TVShow>>>(UiState.Loading)
    val personTVShowsState: LiveData<UiState<List<TVShow>>> get() = _personTVShowsState

    private val _personDetails = MutableLiveData<PersonDetails>()
    val personDetails: LiveData<PersonDetails> get() = _personDetails

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private var personID = 0
    fun loadPersonDetails(personId: Int) {
        personID = personId

        viewModelScope.launch {
            if (connectionTracker.isInternetConnectionAvailable()) {
                getPersonDetails()
            } else {
                _personDetailsState.postValue(UiState.Error(""))
            }
        }

    }

    private fun getPersonDetails() {
        getPersonDetails(personID)
        getPersonMovies(personID)
        getPersonTVShows(personID)
    }

    fun tryLoadMovieDetailsAgain() {
        loadPersonDetails(personID)
    }

    fun onLoadPersonDetailsSuccess(personDetails: PersonDetails) {
        _personDetails.postValue(personDetails)
    }

    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    private fun getPersonDetails(personId: Int) {
        viewModelScope.launch {
            wrapResponse { personRepository.getPersonDetailsById(personId) }.collectLatest {
                _personDetailsState.postValue(it)
            }
        }
    }

    private fun getPersonMovies(personId: Int) {
        viewModelScope.launch {
            wrapResponse { personRepository.getPersonMoviesById(personId) }.collectLatest {
                //_personMoviesState.postValue(it)
            }
        }
    }

    private fun getPersonTVShows(personId: Int) {
        viewModelScope.launch {
            wrapResponse { personRepository.getPersonTVShowsById(personId) }.collectLatest {
                //_personTVShowsState.postValue(it)
            }
        }
    }






}