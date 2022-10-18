package com.octopus.moviesapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.MoviesRepository
import com.octopus.moviesapp.data.repository.TVShowsRepository
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.types.SearchType
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryMovie: MoviesRepository,
) : BaseViewModel(),SearchClicksListener {

    private val _searchResult = MutableLiveData<UiState<List<SearchResult>>>(UiState.Loading)
    val searchResult: LiveData<UiState<List<SearchResult>>> = _searchResult

    private val _navigateToDetails = MutableLiveData<Event<Int>>()
    val navigateToDetails: LiveData<Event<Int>> = _navigateToDetails


    val searchQuery = MutableLiveData("")

    private val _searchFilter = MutableLiveData<String>()
    val searchFilter: LiveData<String> = _searchFilter

    init {
    _searchFilter.value = SearchType.MOVIE.pathName
    }

    fun onChangeSearchQuery() {
        getSearchMultiMedia()
    }

    private fun getSearchMultiMedia() {
        viewModelScope.launch {
            wrapResponse {
                repositoryMovie.getSearchMultiMedia(searchQuery.value.toString())
                    }.debounce(2000).collect {
                        if (it is UiState.Success) {
                            filterByType(it.data, _searchFilter.value.toString())
                        }
            }
        }
    }

    fun onChipMovieSelected() {
        _searchFilter.value = SearchType.MOVIE.pathName
        Log.i("chipMovie = ",  _searchFilter.value.toString())
        getSearchMultiMedia()
    }

    fun onChipTVSelected() {
        _searchFilter.value = SearchType.TV.pathName
        Log.i("chipTv = ",  _searchFilter.value.toString())
        getSearchMultiMedia()
    }

    fun onChipActorSelected() {
        _searchFilter.value = SearchType.PERSON.pathName
        Log.i("chipPerson = ",  _searchFilter.value.toString())
        getSearchMultiMedia()
    }

    private fun filterByType(list: List<SearchResult>, type: String) {
        _searchResult.postValue(UiState.Success(list.filter {
            it.mediaType == type
        }))
    }


    override fun onItemClick(searchId: SearchResult) {
       _navigateToDetails.postEvent(searchId.id)
    }
}