package com.octopus.moviesapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.MoviesRepository
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.domain.types.SearchType
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryMovie: MoviesRepository,
) : BaseViewModel(), SearchClicksListener, ChipGroupClickListener {

    val searchQuery = MutableLiveData("")

    private val _searchResult = MutableLiveData<UiState<List<SearchResult>>>(UiState.Loading)

    private val _navigateToDetails = MutableLiveData<Event<Int>>()

    private val _filteredSearchResults = MutableLiveData<List<SearchResult>>(emptyList())
    val filteredSearchResults: LiveData<List<SearchResult>> = _filteredSearchResults

    val navigateToDetails: LiveData<Event<Int>> = _navigateToDetails

    private val _searchType = MutableLiveData(SearchType.MOVIE.pathName)
    val searchType: LiveData<String> = _searchType


    fun getSearchMultiMedia() {
        viewModelScope.launch {
            wrapResponse {
                repositoryMovie.getSearchMultiMedia(searchQuery.value.toString())
            }.collect {
                _searchResult.postValue(it)
                filterSearchResultsByType(_searchType.value.toString())
            }
        }
    }


    override fun onChipSelected(selectedItemId: Int) {

        when (selectedItemId) {
            0 -> _searchType.value = SearchType.MOVIE.pathName
            1 -> _searchType.value = SearchType.TV.pathName
            2 -> _searchType.value = SearchType.PERSON.pathName
        }

        filterSearchResultsByType(_searchType.value.toString())
    }

    private fun filterSearchResultsByType(type: String) {
        val data = _searchResult.value
        if (data is UiState.Success) {
            _filteredSearchResults.postValue(data.data.filter { it.mediaType == type })
        }
    }


    override fun onItemClick(searchId: Int) {
        _navigateToDetails.postEvent(searchId)
    }
}