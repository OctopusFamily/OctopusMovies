package com.octopus.moviesapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.MoviesRepository
import com.octopus.moviesapp.data.repository.TVShowsRepository
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
import com.octopus.moviesapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryMovie: MoviesRepository,
    private val repositoryTVShow: TVShowsRepository
) : BaseViewModel(), MoviesClicksListener, TVShowsClicksListener {

    private val _searchResult = MutableLiveData<UiState<List<SearchResult>>>(UiState.Loading)
    val searchResult: LiveData<UiState<List<SearchResult>>> = _searchResult

    val searchQuery = MutableLiveData("")
    private val _searchFilter = MutableLiveData("movie")

    fun onChangeSearchQuery() {
        getSearchMultiMedia()
    }

    private fun getSearchMultiMedia() {
        viewModelScope.launch {
            wrapResponse {
                repositoryMovie.getSearchMultiMedia(searchQuery.value.toString())
            }.debounce(
                2000
            ).collect {
                if (it is UiState.Success) {
                    filterByType(it.data, _searchFilter.value.toString())
                }
            }
        }
    }

    fun onChipMovieSelected() {
        _searchFilter.value = "movie"
        getSearchMultiMedia()
    }

    fun onChipTVSelected() {
        _searchFilter.value = "tv"
        getSearchMultiMedia()
    }

    private fun filterByType(list: List<SearchResult>, type: String) {
        _searchResult.postValue(UiState.Success(list.filter {
            it.mediaType == type
        }))
    }

    override fun onMovieClick(movieId: Int) {}

    override fun onTVShowClick(tvShow: TVShow) {}
}