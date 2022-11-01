package com.octopus.moviesapp.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.domain.use_case.moviedetails_usecase.FetchMovieCastUseCase
import com.octopus.moviesapp.domain.use_case.moviedetails_usecase.FetchMovieDetailsUseCase
import com.octopus.moviesapp.domain.use_case.moviedetails_usecase.FetchMovieTrailerUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.movie_details.uistate.MovieDetailsMainUiState
import com.octopus.moviesapp.ui.movie_details.uistate.MovieDetailsUiState
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.GenresUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.TrailerUiState
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
class MovieDetailsViewModel @Inject constructor(
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    private val fetchMovieTrailerUseCase: FetchMovieTrailerUseCase,
    private val fetchMovieCastUseCase: FetchMovieCastUseCase,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel(), NestedGenresListener, NestedCastListener {

    private val _movieDetailsState = MutableStateFlow(MovieDetailsMainUiState())
    val movieDetailsState: StateFlow<MovieDetailsMainUiState> get() = _movieDetailsState

    private val _playTrailer = MutableLiveData<Event<String>>()
    val playTrailer: LiveData<Event<String>> get() = _playTrailer

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _saveToWatchList = MutableLiveData<Event<Int>>()
    val saveToWatchList: LiveData<Event<Int>> get() = _saveToWatchList

    private val _rateMovie = MutableLiveData<Event<Int>>()
    val rateMovie: LiveData<Event<Int>> get() = _rateMovie

    private val _navigateToMoviesGenre = MutableLiveData<Event<Genre>>()
    val navigateToMoviesGenre: LiveData<Event<Genre>> get() = _navigateToMoviesGenre

    private val _navigateToPersonDetails = MutableLiveData<Event<Int>>()
    val navigateToPersonDetails: LiveData<Event<Int>> get() = _navigateToPersonDetails

    private val args = MovieDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    init {
        getMovieData()
    }

    private fun getMovieData() {
        viewModelScope.launch {
            try {
                val trailerResult = fetchMovieTrailerUseCase(args.movieId).asTrailerUiState()
                val detailsResult = fetchMovieDetailsUseCase(args.movieId).asDetailsUiState()
                val castResult = fetchMovieCastUseCase(args.movieId).map{cast -> cast.asCastUiState() }

                onSuccess(trailerResult,detailsResult,castResult)

            } catch (e: Exception) {
                onError()
            }
        }
    }

    private fun onSuccess(
        trailerState: TrailerUiState,
        detailsState: MovieDetailsUiState,
        castState: List<CastUiState>

    ) {
        _movieDetailsState.update {
            it.copy(
                isLoading = false,
                isSuccess = true,
                trailer = trailerState,
                cast = castState,
                info = detailsState
            )
        }
    }
    private fun onError(){
        _movieDetailsState.update { it.copy(isLoading = false, isError = true) }
    }

    // region events
    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    fun onSaveToWatchListClick() {
        _saveToWatchList.postEvent(0)
    }

    fun onPlayTrailerClick(trailer: String) {
        _playTrailer.postEvent(trailer)
    }

    fun onRateClick() {
        _rateMovie.postEvent(0)
    }

    override fun onGenreClick(genre: Genre) {
        _navigateToMoviesGenre.postEvent(genre)
    }

    override fun onCastClick(castId: Int) {
        _navigateToPersonDetails.postEvent(castId)
    }
    // endregion

    private fun Trailer.asTrailerUiState(): TrailerUiState {
        return TrailerUiState(
            url = url
        )
    }
    private fun MovieDetails.asDetailsUiState(): MovieDetailsUiState {
        return MovieDetailsUiState(
            id = id ,
            title = title ,
            coverImageUrl = buildImageUrl(coverImageUrl),
            posterImageUrl = buildImageUrl(posterImageUrl),
            voteCount = voteCount,
            voteAverage = voteAverage,
            originalLanguage = originalLanguage,
            tagline = tagline,
            overview = overview,
            genres = genres.map {genre -> genre.asGenresUiState() },
            runtime = runtime,
            started = releaseDate,
        )
    }
    private fun Genre.asGenresUiState(): GenresUiState {
        return GenresUiState(
            id = id,
            name = name ,
            type = GenresType.TV,
        )
    }

    private fun Cast.asCastUiState(): CastUiState {
        return CastUiState(
            id = id ,
            name = name ,
            profileImageUrl = buildImageUrl(profileImageUrl),
        )
    }


}