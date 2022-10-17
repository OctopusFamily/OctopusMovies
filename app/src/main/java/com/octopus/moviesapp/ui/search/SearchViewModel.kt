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
import com.octopus.moviesapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
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

    private val _searchFilter = MutableLiveData<UiState<List<SearchResult>>>(UiState.Loading)
    val searchFilter: LiveData<UiState<List<SearchResult>>> = _searchFilter

    val searchQuery = MutableLiveData("")


//    fun getSearch() {
//        getSearchMultiMedia()
//        when (_isMovieChipChecked.value) {
//            true -> {
//             filterByMovie()
//            }
//            else -> {
//            filterByTv()
//            }
//        }
//    }

     fun getSearchMultiMedia() {
        viewModelScope.launch {
            wrapResponse { repositoryMovie.getSearchMultiMedia(searchQuery.value.toString()) }.debounce(2000) .collect() {
                _searchResult.postValue(it)
            }
        }
    }


   fun onChipMovieSelected(){
      getSearchMultiMedia()
       filterByMovie()
    }

    fun onChipTVSelected(){
        getSearchMultiMedia()
        filterByTv()
    }

    fun oChipSelected(searchType: SearchType){
        if(searchType == SearchType.MOVIE)
            filterByMovie()
        else
            filterByTv()

    }

    private fun filterByTv() {
        val searchResultValue = _searchResult.value
        if (searchResultValue is UiState.Success) {
            _searchFilter.postValue(UiState.Success(searchResultValue.data.filter { it.mediaType == "tv" }))
            Log.e("searchTV", searchFilter.value.toString())
        }
    }

    private fun filterByMovie() {
        val searchResultValue = _searchResult.value

        if (searchResultValue is UiState.Success) {
            _searchFilter.postValue(UiState.Success(searchResultValue.data.filter { it.mediaType == "movie" }))
            Log.e("searchMovie", searchFilter.value.toString())
        }
    }

    override fun onMovieClick(movieId: Int) {

    }

    override fun onTVShowClick(tvShow: TVShow) {

    }


}