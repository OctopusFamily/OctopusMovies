package com.octopus.moviesapp.ui.tv_show_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.model.*
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.domain.use_case.tvshow_details_use_case.GetTVShowCastUseCase
import com.octopus.moviesapp.domain.use_case.tvshow_details_use_case.GetTVShowDetailsByIdUseCase
import com.octopus.moviesapp.domain.use_case.tvshow_details_use_case.GetTVShowTrailerUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.ui.nested.NestedSeasonsListener
import com.octopus.moviesapp.ui.tv_show_details.uistate.*
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
    private val getTVShowDetails: GetTVShowDetailsByIdUseCase,
    private val getTVShowTrailer: GetTVShowTrailerUseCase,
    private val getTVShowCast: GetTVShowCastUseCase,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel(), NestedGenresListener, NestedCastListener, NestedSeasonsListener {

    private val _tvShowDetailsState = MutableStateFlow(TvShowDetailsMainUiState())
    val tvShowDetailsState: StateFlow<TvShowDetailsMainUiState> get() = _tvShowDetailsState

    private val _rateTvShow = MutableLiveData<Event<Int>>()
    val rateTvShow: LiveData<Event<Int>> get() = _rateTvShow

    private val _playTrailer = MutableLiveData<Event<String>>()
    val playTrailer: LiveData<Event<String>> get() = _playTrailer

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _navigateToTVShowsGenre = MutableLiveData<Event<Genre>>()
    val navigateToTVShowsGenre: LiveData<Event<Genre>> get() = _navigateToTVShowsGenre

    private val _navigateToPersonDetails = MutableLiveData<Event<Int>>()
    val navigateToPersonDetails: LiveData<Event<Int>> get() = _navigateToPersonDetails

    private val args = TVShowDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    init {
        getTVShowData()
    }

    private fun getTVShowData() {
        viewModelScope.launch {
            try {
                val trailerResult = getTVShowTrailer(args.tvShowId).asTrailerUiState()
                val detailsResult = getTVShowDetails(args.tvShowId).asDetailsUiState()
                val castsResult = getTVShowCast(args.tvShowId).map { cast -> cast.asCastUiState() }

                onSuccess(trailerResult, detailsResult, castsResult)

            } catch (e: Exception) {
                onError()
            }
        }
    }

    private fun onSuccess(
        trailerResult: TrailerUiState,
        detailsResult: DetailsUiState,
        castsResult: List<CastUiState>

    ) {
        _tvShowDetailsState.update {
            it.copy(
                isLoading = false,
                isSuccess = true,
                isError = false,
                trailer = trailerResult,
                cast = castsResult,
                info = detailsResult
            )
        }
    }

    private fun onError() {
        _tvShowDetailsState.update {
            it.copy(
                isLoading = false,
                isError = true,
                isSuccess = false
            )
        }
    }

    fun tryLoadTVShowDetailsAgain() {
        _tvShowDetailsState.update {
            it.copy(
                isLoading = true,
                isError = false,
                isSuccess = false,
            )
        }
        getTVShowData()
    }

    fun onPlayTrailerClick(trailer: String) {
        _playTrailer.postEvent(trailer)
    }

    fun onRateClick() {
        _rateTvShow.postEvent(0)
    }

    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    override fun onGenreClick(genre: Genre) {
        _navigateToTVShowsGenre.postEvent(genre)
    }


    override fun onCastClick(castId: Int) {
        _navigateToPersonDetails.postEvent(castId)
    }

    private fun Trailer.asTrailerUiState(): TrailerUiState {
        return TrailerUiState(
            url = url
        )
    }

    private fun TVShowDetails.asDetailsUiState(): DetailsUiState {
        return DetailsUiState(
            id = id,
            title = title,
            coverImageUrl = buildImageUrl(coverImageUrl),
            posterImageUrl = buildImageUrl(posterImageUrl),
            voteCount = voteCount,
            voteAverage = voteAverage,
            episodesNumber = episodesNumber,
            seasonsNumber = seasonsNumber,
            started = started,
            originalLanguage = originalLanguage,
            tagline = tagline,
            overview = overview,
            status = status,
            genres = genres.map { genre -> genre.asGenresUiState() },
            seasons = seasons.map { season -> season.asSeasonUiState() }
        )
    }

    private fun Genre.asGenresUiState(): GenresUiState {
        return GenresUiState(
            id = id,
            name = name,
            type = GenresType.TV,
        )
    }

    private fun Season.asSeasonUiState(): SeasonUiState {
        return SeasonUiState(
            id = id,
            seasonNumber = seasonNumber,
            imageUrl = buildImageUrl(imageUrl),
        )
    }

    private fun Cast.asCastUiState(): CastUiState {
        return CastUiState(
            id = id,
            name = name,
            profileImageUrl = buildImageUrl(profileImageUrl),
        )
    }


}