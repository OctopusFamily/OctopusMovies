package com.octopus.moviesapp.ui.person_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.PersonRepository
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.PersonDetails
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.nested.NestedImageMovieListener
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
) : BaseViewModel(), NestedImageMovieListener {

    private val _personDetailsState = MutableLiveData<UiState<PersonDetails>>(UiState.Loading)
    val personDetailsState: LiveData<UiState<PersonDetails>> get() = _personDetailsState

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _personDetails = MutableLiveData<PersonDetails>()
    val personDetails: LiveData<PersonDetails> get() = _personDetails


    private val _personMoviesState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val personMoviesState: LiveData<UiState<List<Movie>>> get() = _personMoviesState
    private val _personMovies = MutableLiveData<List<Movie>>()
    val personMovies: LiveData<List<Movie>> get() = _personMovies


    private val _personTvShowState = MutableLiveData<UiState<List<TVShow>>>(UiState.Loading)
    val personTvShowState: LiveData<UiState<List<TVShow>>> get() = _personTvShowState
    private val _personTvShow = MutableLiveData<List<TVShow>>()
    val personTvShow: LiveData<List<TVShow>> get() = _personTvShow

    private var personId: Int = 0

    fun loadPersonDetailsData(personId: Int) {
        this.personId = personId
        Log.v("ameer", " personId in PersonDetailsViewModel  $personId")
        viewModelScope.launch {
            if (connectionTracker.isInternetConnectionAvailable()) {
                getPersonDetailsData()
                getPersonMoviesData()
            } else {
                _personDetailsState.postValue(UiState.Error(Constants.ERROR_INTERNET))
            }
        }
    }

    private fun getPersonDetailsData() {
        viewModelScope.launch {
            wrapResponse { personRepository.getPersonDetailsById(personId) }.collectLatest {
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
            wrapResponse { personRepository.getPersonMoviesById(personId) }.collectLatest {
                _personMoviesState.postValue(it)
            }
        }

    }

    private fun getPersonTvShowData() {
        viewModelScope.launch {
            wrapResponse { personRepository.getPersonTVShowsById(personId) }.collectLatest {
                _personTvShowState.postValue(it)
            }
        }

    }

    fun onLoadPersonMoviesSuccess(movies: List<Movie>) {
        Log.v("ameer", "onLoadPersonMoviesSuccess ${movies[0].posterImageUrl}")
        _personMovies.postValue(movies)
    }

    fun tryLoadPersonDetailAgain() {
        loadPersonDetailsData(personId)
    }


    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    override fun onImageMovieClick(movieId: Int) {
//        TODO("Not yet implemented")
    }
//
//    private val personRepository: PersonRepository,
//    private val connectionTracker: ConnectionTracker,
//) : BaseViewModel() {
//
//    private val _personDetailsState = MutableLiveData<UiState<PersonDetails>>(UiState.Loading)
//    val personDetailsState: LiveData<UiState<PersonDetails>> get() = _personDetailsState
//
//    private val _personMoviesState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
//    val personMoviesState: LiveData<UiState<List<Movie>>> get() = _personMoviesState
//
//    private val _personTVShowsState = MutableLiveData<UiState<List<TVShow>>>(UiState.Loading)
//    val personTVShowsState: LiveData<UiState<List<TVShow>>> get() = _personTVShowsState
//
//    private val _personDetails = MutableLiveData<PersonDetails>()
//    val personDetails: LiveData<PersonDetails> get() = _personDetails
//
//    private val _navigateBack = MutableLiveData<Event<Boolean>>()
//    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack
//
//    private var personID = 0
//    fun loadPersonDetails(personId: Int) {
//        personID = personId
//
//        viewModelScope.launch {
//            if (connectionTracker.isInternetConnectionAvailable()) {
//                getPersonDetails()
//            } else {
//                _personDetailsState.postValue(UiState.Error(""))
//            }
//        }
//
//    }
//
//    private fun getPersonDetails() {
//        getPersonDetails(personID)
//        getPersonMovies(personID)
//        getPersonTVShows(personID)
//    }
//
//    fun tryLoadPersonDetailsAgain() {
//        loadPersonDetails(personID)
//    }
//
//    fun onLoadPersonDetailsSuccess(personDetails: PersonDetails) {
//        _personDetails.postValue(personDetails)
//    }
//
//    fun onNavigateBackClick() {
//        _navigateBack.postEvent(true)
//    }
//
//    private fun getPersonDetails(personId: Int) {
//        viewModelScope.launch {
//            wrapResponse { personRepository.getPersonDetailsById(personId) }.collectLatest {
//                _personDetailsState.postValue(it)
//            }
//        }
//    }
//
//    private fun getPersonMovies(personId: Int) {
//        viewModelScope.launch {
//            wrapResponse { personRepository.getPersonMoviesById(personId) }.collectLatest {
//                _personMoviesState.postValue(it)
//            }
//        }
//    }
//
//    private fun getPersonTVShows(personId: Int) {
//        viewModelScope.launch {
//            wrapResponse { personRepository.getPersonTVShowsById(personId) }.collectLatest {
//                _personTVShowsState.postValue(it)
//            }
//        }
//    }


}