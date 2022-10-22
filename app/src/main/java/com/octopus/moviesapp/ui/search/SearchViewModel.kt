package com.octopus.moviesapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.domain.types.MediaType
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryMovie: MoviesRepository,
) : BaseViewModel(), SearchClicksListener, ChipGroupClickListener {

    val searchQuery = MutableLiveData<String>()

    private val _searchResult = MutableLiveData<UiState<List<SearchResult>>>(UiState.Loading)
    val searchResult: LiveData<UiState<List<SearchResult>>> = _searchResult

    private val _filteredSearchResults = MutableLiveData<List<SearchResult>>(emptyList())
    val filteredSearchResults: LiveData<List<SearchResult>> = _filteredSearchResults

    private val searchResultItems = MutableLiveData<List<SearchResult>>()

    private val _navigateToDetails = MutableLiveData<Event<Int>>()
    val navigateToDetails: LiveData<Event<Int>> = _navigateToDetails

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    var mediaType = MediaType.MOVIE

    fun getSearchMultiMedia(searchQuery: String) {
        if (searchQuery.isNotEmpty()) {
            viewModelScope.launch {
                wrapResponse { repositoryMovie.getSearchMultiMedia(searchQuery) }.collectLatest { searchState ->
                    if (searchState is UiState.Success) {
                        filterSearchResultsByType(searchState.data, mediaType)
                        searchState.data.let { searchResultItems.postValue(it) }
                    }
                    _searchResult.postValue(searchState)
                }
            }

        } else _filteredSearchResults.postValue(emptyList())
    }

    fun tryLoadDataAgain(searchQuery: String) {
        getSearchMultiMedia(searchQuery)
    }

    fun onBackClick() {
        _navigateBack.postEvent(true)
    }

    override fun onChipSelected(selectedItemId: Int) {
        when (selectedItemId) {
            0 -> mediaType = MediaType.MOVIE
            1 -> mediaType = MediaType.TV
            2 -> mediaType = MediaType.PERSON
        }

        searchQuery.value?.let { query ->
            if (query.isEmpty()) {
                clearList()
            } else {
                filterCurrentList()
            }
        } ?: clearList()
    }

    private fun clearList() {
        _filteredSearchResults.postValue(emptyList())
    }

    private fun filterCurrentList() {
        searchResultItems.value?.let {
            filterSearchResultsByType(it, mediaType)
        }
    }

    private fun filterSearchResultsByType(data: List<SearchResult>, type: MediaType) {
        _filteredSearchResults.postValue(data.filter { it.mediaType == type })
    }

    override fun onItemClick(searchId: Int) {
        _navigateToDetails.postEvent(searchId)
    }
}