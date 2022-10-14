package com.octopus.moviesapp.ui.tv_shows_genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.MainRepository
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowsGenreViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel(), TVShowsClicksListener {


    private val _tvShowGenreState = MutableLiveData<UiState<List<TVShow>>>(UiState.Loading)
    val tvShowGenreState: LiveData<UiState<List<TVShow>>> get() = _tvShowGenreState

    private val _genreName = MutableLiveData<String>("")
    val genreName: LiveData<String> get() = _genreName


    private val _navigateToTVShowDetails = MutableLiveData<Event<Int>>()
    val navigateToTVShowDetails : LiveData<Event<Int>> = _navigateToTVShowDetails

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private var genreID = 0
    fun loadTVShow(genreId: Int, genreName: String) {
        genreID = genreId
        getTVShowByGenreId(genreId)
        _genreName.postValue(genreName)
    }

    private fun getTVShowByGenreId(genreId: Int) {
        viewModelScope.launch {
            wrapResponse { repository.getListOfTVShowsByGenresId(genreId) }.collectLatest {
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