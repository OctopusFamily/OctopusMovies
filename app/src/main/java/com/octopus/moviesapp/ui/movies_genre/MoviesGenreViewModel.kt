package com.octopus.moviesapp.ui.movies_genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.GenresRepository
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesGenreViewModel @Inject constructor(
    private val genresRepository: GenresRepository,
) : BaseViewModel(), MoviesClicksListener {


    private val _movieGenreState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val movieGenreState: LiveData<UiState<List<Movie>>> get() = _movieGenreState

    private val _genreName = MutableLiveData<String>("")
    val genreName: LiveData<String> get() = _genreName


    private val _navigateToMovieDetails = MutableLiveData<Event<Int>>()
    val navigateToMovieDetails: LiveData<Event<Int>> = _navigateToMovieDetails

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private var genreID = 0
    fun loadMovies(genreId: Int, genreName: String) {
        genreID = genreId
        getMoviesByGenreId(genreId)
        _genreName.postValue(genreName)
    }

    private fun getMoviesByGenreId(genreId: Int) {
        viewModelScope.launch {
            wrapResponse { genresRepository.getListOfMoviesByGenresId(genreId) }.collectLatest {
                _movieGenreState.postValue(it)
            }
        }
    }

    override fun onMovieClick(movieId: Int) {
        _navigateToMovieDetails.postEvent(movieId)
    }

    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }


}