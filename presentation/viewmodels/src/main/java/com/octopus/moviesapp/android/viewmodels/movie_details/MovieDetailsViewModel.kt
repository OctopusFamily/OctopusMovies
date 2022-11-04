package com.octopus.moviesapp.android.viewmodels.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.octopus.moviesapp.android.mapper.buildImageUrl
import com.octopus.moviesapp.android.usecases.movies.FetchMovieCastUseCase
import com.octopus.moviesapp.android.usecases.movies.FetchMovieDetailsUseCase
import com.octopus.moviesapp.android.usecases.movies.FetchMovieTrailerUseCase
import com.octopus.moviesapp.android.viewmodels.movie_details.uistate.MovieDetailsMainUiState
import com.octopus.moviesapp.android.viewmodels.movie_details.uistate.MovieDetailsUiState
import com.octopus.moviesapp.android.viewmodels.movie_details.utiles.Event
import com.octopus.moviesapp.models.model.Cast
import com.octopus.moviesapp.models.model.Genre
import com.octopus.moviesapp.models.model.MovieDetails
import com.octopus.moviesapp.models.model.Trailer
import com.octopus.moviesapp.models.type.GenresType
import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.GenresUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetails: FetchMovieDetailsUseCase,
    private val getMovieTrailer: FetchMovieTrailerUseCase,
    private val getMovieCast: FetchMovieCastUseCase,
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

    private val _isSaveIconClicked = MutableLiveData(Event(false))
    val isSaveIconClicked = _isSaveIconClicked

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
                val trailerResult = getMovieTrailer(args.movieId).asTrailerUiState()
                val detailsResult = getMovieDetails(args.movieId).asDetailsUiState()
                val castResult = getMovieCast(args.movieId).map { cast -> cast.asCastUiState() }

                onSuccess(trailerResult, detailsResult, castResult)

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
                isError = false,
                trailer = trailerState,
                cast = castState,
                info = detailsState
            )
        }
    }

    private fun onError() {
        _movieDetailsState.update {
            it.copy(
                isLoading = false,
                isError = true,
                isSuccess = false
            )
        }
    }

    fun tryLoadMovieDetailsAgain() {
        _movieDetailsState.update {
            it.copy(
                isLoading = true,
                isError = false,
                isSuccess = false
            )
        }
        getMovieData()
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

    fun onSaveClick(){
        _isSaveIconClicked.postValue(Event(true))
    }

    override fun onGenreClick(genre: Genre) {
        _navigateToMoviesGenre.postEvent(genre)
    }

    override fun onCastClick(castId: Int) {
        _navigateToPersonDetails.postEvent(castId)
    }

    private fun Trailer.asTrailerUiState(): TrailerUiState {
        return TrailerUiState(
            url = url
        )
    }

    private fun MovieDetails.asDetailsUiState(): MovieDetailsUiState {
        return MovieDetailsUiState(
            id = id,
            title = title,
            coverImageUrl = buildImageUrl(coverImageUrl),
            posterImageUrl = buildImageUrl(posterImageUrl),
            voteCount = voteCount,
            voteAverage = voteAverage,
            originalLanguage = originalLanguage,
            tagline = tagline,
            overview = overview,
            genres = genres.map { genre -> genre.asGenresUiState() },
            runtime = runtime,
            started = releaseDate,
        )
    }

    private fun Genre.asGenresUiState(): GenresUiState {
        return GenresUiState(
            id = id,
            name = name,
            type = GenresType.TV,
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