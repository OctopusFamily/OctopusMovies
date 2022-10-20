package com.octopus.moviesapp.ui.tv_shows_genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.GenresRepository
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
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
class TVShowsGenreViewModel @Inject constructor(
    private val genresRepository: GenresRepository,
    private val connectionTracker: ConnectionTracker,
) : BaseViewModel(), TVShowsClicksListener {


    private val _tvShowGenreState = MutableLiveData<UiState<List<TVShow>>>(UiState.Loading)
    val tvShowGenreState: LiveData<UiState<List<TVShow>>> get() = _tvShowGenreState

    private val _genreName = MutableLiveData("")
    val genreName: LiveData<String> get() = _genreName


    private val _navigateToTVShowDetails = MutableLiveData<Event<Int>>()
    val navigateToTVShowDetails : LiveData<Event<Int>> = _navigateToTVShowDetails

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private var genreID = 0
    fun loadTVShow(genreId: Int, genreName: String) {
        genreID = genreId
        _genreName.postValue(genreName)

        getLoadTVShow()


    }

    private fun getLoadTVShow() {
        viewModelScope.launch {
            if (connectionTracker.isInternetConnectionAvailable()) {
                getTVShowByGenreId(genreID)
            } else {
                _tvShowGenreState.postValue(UiState.Error(Constants.ERROR_INTERNET))
            }
        }

    }

    fun tryLoadTVShow() {
        getLoadTVShow()
    }

    private fun getTVShowByGenreId(genreId: Int) {
        viewModelScope.launch {
            wrapResponse { genresRepository.getListOfTVShowsByGenresId(genreId) }.collectLatest {
                _tvShowGenreState.postValue(it)
            }
        }
    }


    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    override fun onTVShowClick(tvShow: TVShow) {
        _navigateToTVShowDetails.postEvent(tvShow.id)
    }


}