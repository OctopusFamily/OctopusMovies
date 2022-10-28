package com.octopus.moviesapp.ui.tv_show_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Season
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.domain.tvshow_details_use_case.FetchTVShowCastUseCase
import com.octopus.moviesapp.domain.tvshow_details_use_case.FetchTVShowDetailsByIdUseCase
import com.octopus.moviesapp.domain.tvshow_details_use_case.FetchTVShowTrailerUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.ui.nested.NestedSeasonsListener
import com.octopus.moviesapp.ui.tv_show_details.tvShowCastState.TVShowCastUiStateMapper
import com.octopus.moviesapp.ui.tv_show_details.tvShowCastState.TvShowCastMainUiState
import com.octopus.moviesapp.ui.tv_show_details.tvShowDetailsState.TVShowDetailsUiStateMapper
import com.octopus.moviesapp.ui.tv_show_details.tvShowDetailsState.TvShowDetailsMainUiState
import com.octopus.moviesapp.ui.tv_show_details.tvShowTrailerState.TVShowTrailerUiState
import com.octopus.moviesapp.ui.tv_show_details.tvShowTrailerState.TVShowTrailerUiStateMapper
import com.octopus.moviesapp.ui.tv_show_details.tvShowTrailerState.TvShowTrailerMainUiState
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val fetchTVShowDetailsByIdUseCase: FetchTVShowDetailsByIdUseCase,
    private val fetchTVShowTrailerUseCase: FetchTVShowTrailerUseCase,
    private val fetchTVShowCastUseCase: FetchTVShowCastUseCase,
    private val tvShowDetailsUiStateMapper: TVShowDetailsUiStateMapper,
    private val tvShowTrailerUiStateMapper: TVShowTrailerUiStateMapper,
    private val tvShowCastUiStateMapper: TVShowCastUiStateMapper,
    private val tvShowsRepository: TVShowsRepository,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel(), NestedGenresListener, NestedCastListener, NestedSeasonsListener {

    private val _tvShowDetailsState = MutableStateFlow(TvShowDetailsMainUiState())
    val tvShowDetailsState: StateFlow<TvShowDetailsMainUiState> get() = _tvShowDetailsState

    private val _tvShowTrailerState = MutableStateFlow(TvShowTrailerMainUiState())
    val tvShowTrailerState: StateFlow<TvShowTrailerMainUiState> get() = _tvShowTrailerState

    private val _tvShowCastState = MutableStateFlow(TvShowCastMainUiState())
    val tvShowCastState:  StateFlow<TvShowCastMainUiState> get() = _tvShowCastState



//    private val _tvShowDetailsState = MutableLiveData<UiState<TVShowDetails>>(UiState.Loading)
//    val tvShowDetailsState: LiveData<UiState<TVShowDetails>> get() = _tvShowDetailsState

//    private val _tvTrailerState = MutableLiveData<UiState<Trailer>>(UiState.Loading)
//    val tvTrailerState: LiveData<UiState<Trailer>> get() = _tvTrailerState

//    private val _tvShowCastState = MutableLiveData<UiState<List<Cast>>>(UiState.Loading)
//    val tvShowCastState: LiveData<UiState<List<Cast>>> get() = _tvShowCastState


    private val _rateTvShow = MutableLiveData<Event<Int>>()
    val rateTvShow: LiveData<Event<Int>> get() = _rateTvShow

    private val _playTrailer = MutableLiveData<Event<String>>()
    val playTrailer: LiveData<Event<String>> get() = _playTrailer

    private val _tvTrailer = MutableLiveData<TVShowTrailerUiState>()

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

    }

    private fun getTVShowDetailsInfo() {
        getTVShowDetails(args.tvShowId)
        getTVShowCast(args.tvShowId)
        getTVTrailer(args.tvShowId)
    }

    //
//    //use case: fetchTVShowTrailerUseCase
    private fun getTVTrailer(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val result = fetchTVShowTrailerUseCase(tvShowId)
                val tvShowTrailerState = tvShowTrailerUiStateMapper.map(result)
                _tvShowTrailerState.update {
                    it.copy(isLoading = false, isSuccess = true, tvShowTrailersUiState = tvShowTrailerState
                    )
                }
            } catch (e: Exception) {
                _tvShowTrailerState.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    //use case: fetchTVShowDetailsByIdUseCase
    private fun getTVShowDetails(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val result = fetchTVShowDetailsByIdUseCase(tvShowId)
                val tvShowDetailsState = tvShowDetailsUiStateMapper.map(result)
                _tvShowDetailsState.update {
                    it.copy(isLoading = false, isSuccess = true, tvShowDetailsUiState = tvShowDetailsState
                    )
                }
            } catch (e: Exception) {
                _tvShowDetailsState.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }


//    //use case: fetchCastByIdUseCase
    private fun getTVShowCast(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val result = fetchTVShowCastUseCase(tvShowId)
                val tvShowCastsState = tvShowCastUiStateMapper.map(result)
                _tvShowCastState.update {
                    it.copy(isLoading = false, isSuccess = true, tVShowCastUiState = tvShowCastsState
                    )
                }
            } catch (e: Exception) {
                _tvShowCastState.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun onLoadTrailerSuccess(tvTrailer: TVShowTrailerUiState) {
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


    override fun onGenreClick(genre: Genre) {
        _navigateToTVShowsGenre.postEvent(genre)
    }

    override fun onCastClick(castId: Int) {
        _navigateToPersonDetails.postEvent(castId)
    }
}