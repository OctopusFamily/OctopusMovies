package com.octopus.moviesapp.ui.person_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.PersonDetails
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.use_case.GetPersonDetailsUseCase
import com.octopus.moviesapp.domain.use_case.GetPersonMoviesUseCase
import com.octopus.moviesapp.domain.use_case.GetPersonTVShowsUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.nested.NestedImageMovieListener
import com.octopus.moviesapp.ui.nested.NestedImageTVShowListener
import com.octopus.moviesapp.ui.person_details.uistate.PersonDetailsMainUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonDetailsUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonMovieUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonTVShowUiState
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val getPersonDetails: GetPersonDetailsUseCase,
    private val getPersonMovies: GetPersonMoviesUseCase,
    private val getPersonTvShow: GetPersonTVShowsUseCase,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel(), NestedImageMovieListener, NestedImageTVShowListener {

    private val _personDetailsState = MutableStateFlow(PersonDetailsMainUiState())
    val personDetailsState: StateFlow<PersonDetailsMainUiState> get() = _personDetailsState

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _navigateToMovieDetails = MutableLiveData<Event<Int>>()
    val navigateToMovieDetails: LiveData<Event<Int>> get() = _navigateToMovieDetails

    private val _navigateToTVShowDetails = MutableLiveData<Event<Int>>()
    val navigateToTVShowDetails: LiveData<Event<Int>> get() = _navigateToTVShowDetails


    private val args = PersonDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    init {
        loadPersonDetailsData()
    }

    private fun loadPersonDetailsData() {
        viewModelScope.launch {
            try {
                val personDetails = getPersonDetailsData()
                val personMovies = getPersonMoviesData()
                val personTVShows = getPersonTVShowsData()

                _personDetailsState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        detailsUiState = personDetails,
                        moviesUiState = personMovies,
                        TVShowUiState = personTVShows
                    )
                }
            } catch (e: Exception) {
                _personDetailsState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        isSuccess = false
                    )
                }
            }
        }
    }

    private suspend fun getPersonDetailsData(): PersonDetailsUiState {
        return getPersonDetails(args.personId).asPersonDetailsUiState()
    }

    private suspend fun getPersonMoviesData(): List<PersonMovieUiState> {
        return getPersonMovies(args.personId).map {
            it.asPersonMovieUiState()
        }
    }

    private suspend fun getPersonTVShowsData(): List<PersonTVShowUiState> {
        return getPersonTvShow(args.personId).map {
            it.asPersonTVShowUiState()
        }
    }

    fun tryLoadPersonDetailAgain() {
        _personDetailsState.update {
            it.copy(
                isLoading = true,
                isError = false,
                isSuccess = false,
            )
        }
        loadPersonDetailsData()
    }


    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    override fun onImageMovieClick(movieId: Int) {
        _navigateToMovieDetails.postEvent(movieId)
    }

    override fun onImageTvShowClick(tvShowId: Int) {
        _navigateToTVShowDetails.postEvent(tvShowId)
    }

    private fun PersonDetails.asPersonDetailsUiState(): PersonDetailsUiState {
        return PersonDetailsUiState(
            name = name,
            profilePath = profilePath,
            biography = biography,
            birthday = birthday,
            knownForDepartment = knownForDepartment,
            popularity = popularity,
            placeOfBirth = placeOfBirth,
        )
    }

    private fun Movie.asPersonMovieUiState(): PersonMovieUiState {
        return PersonMovieUiState(
            id = id, posterImageUrl = posterImageUrl
        )
    }

    private fun TVShow.asPersonTVShowUiState(): PersonTVShowUiState {
        return PersonTVShowUiState(
            id = id, posterImageUrl = posterImageUrl
        )
    }


}