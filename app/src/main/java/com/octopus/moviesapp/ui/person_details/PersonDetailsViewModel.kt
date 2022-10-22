package com.octopus.moviesapp.ui.person_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.person.PersonRepository
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.PersonDetails
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.nested.NestedImageMovieListener
import com.octopus.moviesapp.ui.nested.NestedImageTvShowListener
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
class PersonDetailsViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val connectionTracker: ConnectionTracker,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel(), NestedImageMovieListener, NestedImageTvShowListener {

    private val _personDetailsState = MutableLiveData<UiState<PersonDetails>>(UiState.Loading)
    val personDetailsState: LiveData<UiState<PersonDetails>> get() = _personDetailsState

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _personDetails = MutableLiveData<PersonDetails>()
    val personDetails: LiveData<PersonDetails> get() = _personDetails

    private val _personMoviesState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val personMoviesState: LiveData<UiState<List<Movie>>> get() = _personMoviesState

    private val _personTvShowState = MutableLiveData<UiState<List<TVShow>>>(UiState.Loading)
    val personTvShowState: LiveData<UiState<List<TVShow>>> get() = _personTvShowState

    private val args = PersonDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    init {
        loadPersonDetailsData()
    }

    private fun loadPersonDetailsData() {
        viewModelScope.launch {
            if (connectionTracker.isInternetConnectionAvailable()) {
                getPersonDetailsData()
                getPersonMoviesData()
                getPersonTvShowData()
            } else {
                _personDetailsState.postValue(UiState.Error(Constants.ERROR_INTERNET))
            }
        }
    }

    private fun getPersonDetailsData() {
        viewModelScope.launch {
            wrapResponse { personRepository.getPersonDetailsById(args.personId) }.collectLatest {
                _personDetailsState.postValue(it)
            }
        }
    }

    fun onLoadPersonDetailSuccess(personDetail: PersonDetails) {
        _personDetails.postValue(personDetail)
        _personDetailsState.postValue(UiState.Success(personDetail))
    }

    private fun getPersonMoviesData() {
        viewModelScope.launch {
            wrapResponse { personRepository.getPersonMoviesById(args.personId) }.collectLatest {
                _personMoviesState.postValue(it)
            }
        }
    }

    private fun getPersonTvShowData() {
        viewModelScope.launch {
            wrapResponse { personRepository.getPersonTVShowsById(args.personId) }.collectLatest {
                _personTvShowState.postValue(it)
            }
        }
    }

    fun tryLoadPersonDetailAgain() {
        loadPersonDetailsData()
    }


    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    override fun onImageMovieClick(movieId: Int) {

    }

    override fun onImageTvShowClick(movieId: Int) {

    }


}