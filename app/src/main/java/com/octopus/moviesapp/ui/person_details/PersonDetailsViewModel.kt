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
import com.octopus.moviesapp.domain.use_case.GetPersonTvShowUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.nested.NestedImageMovieListener
import com.octopus.moviesapp.ui.nested.NestedImageTvShowListener
import com.octopus.moviesapp.ui.person_details.uistate.PersonDetailsMainUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonDetailsUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonMoviesUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonTvShowUiState
import com.octopus.moviesapp.util.ConnectionTracker
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
    private val getPersonTvShow: GetPersonTvShowUseCase,

    private val connectionTracker: ConnectionTracker,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel(), NestedImageMovieListener, NestedImageTvShowListener {

    private val _personDetailsState = MutableStateFlow(PersonDetailsMainUiState())
    val personDetailsState: StateFlow<PersonDetailsMainUiState> get() = _personDetailsState

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack


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
                _personDetailsState.update { it.copy(isError = true) }
            }
        }
    }

    private fun getPersonDetailsData() {
        viewModelScope.launch {

            try {
                val personDetails = getPersonDetails.invoke(args.personId).toUiState()
                _personDetailsState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        personDetailsUiState = personDetails
                    )
                }
            } catch (error: Throwable) {
                _personDetailsState.update { it.copy(isError = true) }
            }

        }
    }


    private fun getPersonMoviesData() {

        viewModelScope.launch {

            try {
                val personTvShow = getPersonTvShow.invoke(args.personId).toUiState()
                _personDetailsState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        personTvShowUiState = personTvShow
                    )
                }
            } catch (error: Throwable) {
                _personDetailsState.update { it.copy(isError = true) }
            }

        }

    }

    private fun getPersonTvShowData() {
        viewModelScope.launch {

            try {
                val personMovies = getPersonMovies.invoke(args.personId).toUiState()
                _personDetailsState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        personMoviesUiState = personMovies
                    )
                }
            } catch (error: Throwable) {
                _personDetailsState.update { it.copy(isError = true) }
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

    private fun PersonDetails.toUiState(): PersonDetailsUiState {
        return PersonDetailsUiState(
            name = this.name,
            profilePath = this.profilePath,
            biography = this.biography,
            birthday = this.birthday,
            knownForDepartment = this.knownForDepartment,
            popularity = this.popularity,
            placeOfBirth = this.placeOfBirth,
        )
    }

    private fun List<Movie>.toUiState(): List<PersonMoviesUiState> {
        return this.map {
            PersonMoviesUiState(
                id = it.id,
                posterImageUrl = it.posterImageUrl
            )
        }
    }

    @JvmName("toUiStateTVShow")
    private fun List<TVShow>.toUiState(): List<PersonTvShowUiState> {
        return this.map {
            PersonTvShowUiState(
                id = it.id,
                posterImageUrl = it.posterImageUrl
            )
        }
    }
}