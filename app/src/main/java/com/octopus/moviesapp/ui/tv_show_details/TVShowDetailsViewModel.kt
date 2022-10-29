package com.octopus.moviesapp.ui.tv_show_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.use_case.tvshow_details_use_case.GetTVShowCastUseCase
import com.octopus.moviesapp.domain.use_case.tvshow_details_use_case.GetTVShowDetailsByIdUseCase
import com.octopus.moviesapp.domain.use_case.tvshow_details_use_case.GetTVShowTrailerUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.ui.nested.NestedSeasonsListener
import com.octopus.moviesapp.ui.tv_show_details.mappers.*
import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.TVShowDetailsUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.TrailerUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.TvShowDetailsMainUiState
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val getTVShowDetailsByIdUseCase: GetTVShowDetailsByIdUseCase,
    private val getTVShowTrailerUseCase: GetTVShowTrailerUseCase,
    private val getTVShowCastUseCase: GetTVShowCastUseCase,
    private val tvShowDetailsUiStateMapper: TVShowDetailsUiStateMapper,
    private val tvShowTrailerUiStateMapper: TVShowTrailerUiStateMapper,
    private val tvShowCastUiStateMapper: CastUiStateMapper,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel(), NestedGenresListener, NestedCastListener, NestedSeasonsListener {

    private val _tvShowDetailsState = MutableStateFlow(TvShowDetailsMainUiState())
    val tvShowDetailsState: StateFlow<TvShowDetailsMainUiState> get() = _tvShowDetailsState
    
    private val _rateTvShow = MutableLiveData<Event<Int>>()
    val rateTvShow: LiveData<Event<Int>> get() = _rateTvShow

    private val _playTrailer = MutableLiveData<Event<String>>()
    val playTrailer: LiveData<Event<String>> get() = _playTrailer

    private val _tvTrailer = MutableLiveData<TrailerUiState>()


    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _tvShowDetails = MutableStateFlow(TVShowDetailsUiState())
    val tvShowDetails: StateFlow<TVShowDetailsUiState> get() = _tvShowDetails

    private val _navigateToTVShowsGenre = MutableLiveData<Event<Genre>>()
    val navigateToTVShowsGenre: LiveData<Event<Genre>> get() = _navigateToTVShowsGenre

    private val _navigateToPersonDetails = MutableLiveData<Event<Int>>()
    val navigateToPersonDetails: LiveData<Event<Int>> get() = _navigateToPersonDetails

    private val args = TVShowDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    init {
        getTVShowData(args.tvShowId)
    }

    private fun getTVShowData(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val TrailerResult = getTVShowTrailerUseCase(tvShowId)
                val DetailsResult = getTVShowDetailsByIdUseCase(tvShowId)
                val CastsResult = getTVShowCastUseCase(tvShowId)

                val tvShowTrailerState = tvShowTrailerUiStateMapper.map(TrailerResult)
                val tvShowDetailsState = tvShowDetailsUiStateMapper.map(DetailsResult)
                val tvShowCastsState = tvShowCastUiStateMapper.map(CastsResult)

                onSuccess(tvShowTrailerState,tvShowCastsState,tvShowDetailsState)

            } catch (e: Exception) {
               onError()
            }
        }
    }

    private fun onSuccess(
        tvShowTrailerState: TrailerUiState,
        tvShowCastsState: List<CastUiState>,
        tvShowDetailsState: TVShowDetailsUiState

    ) {
        _tvShowDetailsState.update {
            it.copy(
                isLoading = false,
                isSuccess = true,
                trailer = tvShowTrailerState,
                cast = tvShowCastsState,
                Info = tvShowDetailsState
            )
        }
    }

    private fun onError(){
        _tvShowDetailsState.update { it.copy(isLoading = false, isError = true) }
    }



    fun onLoadTrailerSuccess(tvTrailer: TrailerUiState) {
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


    fun onLoadTVShowDetailsSuccess(tvShowDetails: TVShowDetailsUiState) {
        viewModelScope.launch {
            _tvShowDetails.emit(tvShowDetails)
        }
    }

    fun tryLoadTVShowDetailsAgain() {
        getTVShowData(args.tvShowId)
    }

    override fun onGenreClick(genre: Genre) {
        _navigateToTVShowsGenre.postEvent(genre)
    }

    override fun onCastClick(castId: Int) {
        _navigateToPersonDetails.postEvent(castId)
    }

}