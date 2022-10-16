package com.octopus.moviesapp.ui.search

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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryMovie: MoviesRepository,
    private val repositoryTVShow: TVShowsRepository
) : BaseViewModel(), MoviesClicksListener, TVShowsClicksListener {

    private val _isMovieChipChecked = MutableLiveData(true)

    private val _searchResult = MutableLiveData<UiState<List<SearchResult>>>(UiState.Loading)
    val searchResult: LiveData<UiState<List<SearchResult>>> = _searchResult

    val searchQuery = MutableLiveData("")


    fun getSearch() {
        getSearchMultiMedia()
        when (_isMovieChipChecked.value) {
            true -> {
                val searchResultValue = _searchResult.value

                if (searchResultValue is UiState.Success) {
                    _searchResult.postValue(UiState.Success(searchResultValue.data.filter { it.mediaType == "movie" }))
                }
            }
            else -> {
                val searchResultValue = _searchResult.value
                if (searchResultValue is UiState.Success) {
                    _searchResult.postValue(UiState.Success(searchResultValue.data.filter { it.mediaType == "tv" }))
                }
            }
        }
    }

    private fun getSearchMultiMedia() {
        viewModelScope.launch {
            wrapResponse { repositoryMovie.getSearchMultiMedia(searchQuery.value.toString()) }.collectLatest {
                _searchResult.postValue(it)
            }
        }
    }

    fun filterByTv() {
        _isMovieChipChecked.postValue(false)
    }

    fun filterByMovie() {
        _isMovieChipChecked.postValue(true)
    }

    override fun onMovieClick(movieId: Int) {

    }

    override fun onTVShowClick(tvShow: TVShow) {

    }


}