package com.octopus.moviesapp.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.GenresRepository
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.ui.base.BaseViewModel
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
class GenresViewModel @Inject constructor(
    private val genresRepository: GenresRepository,
    private val connectionTracker: ConnectionTracker,
) : BaseViewModel(), GenresClicksListener {

    private val _genresListState = MutableLiveData<UiState<List<Genre>>>(UiState.Loading)
    val genresListState: LiveData<UiState<List<Genre>>> get() = _genresListState

    private val _navigateToGenreMovie = MutableLiveData<Event<Genre>>()
    val navigateToGenreMovie: LiveData<Event<Genre>> get() = _navigateToGenreMovie

    private val _navigateToGenreTVShow = MutableLiveData<Event<Genre>>()
    val navigateToGenreTVShow: LiveData<Event<Genre>> get() = _navigateToGenreTVShow

    private var currentGenresType = GenresType.MOVIE

    init {
        currentGenresType
       getGenresByList(currentGenresType)
    }

    override fun onGenreClick(genre: Genre) {
        when (genre.type) {
            GenresType.MOVIE -> {
                _navigateToGenreMovie.postEvent(genre)
            }
            GenresType.TV -> {
                _navigateToGenreTVShow.postEvent(genre)
            }
        }
    }

    fun onTapSelected(genresType: GenresType) {
        currentGenresType = genresType
        getGenresByList(genresType)
    }

    fun tryLoadGenresAgain() {
        getGenresByList(currentGenresType)
    }

    private fun getGenresByList(currentGenresType: GenresType) {
        viewModelScope.launch {
            if (connectionTracker.isInternetConnectionAvailable()) {
                loadGenresByList(currentGenresType)
            } else {
                _genresListState.postValue(UiState.Error(Constants.ERROR_INTERNET))
            }
        }


    }

    private fun loadGenresByList(currentGenresType: GenresType) {
        viewModelScope.launch {
            wrapResponse { genresRepository.getGenresByType(currentGenresType) }.collectLatest {
                _genresListState.postValue(it)
            }
        }
    }


}