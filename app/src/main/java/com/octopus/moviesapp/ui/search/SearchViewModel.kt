package com.octopus.moviesapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.domain.types.MediaType
import com.octopus.moviesapp.domain.use_case.search.FilterSearchResultsUseCase
import com.octopus.moviesapp.domain.use_case.search.SearchMediaUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMediaUseCase: SearchMediaUseCase,
    private val filterSearchUseCase: FilterSearchResultsUseCase,
) : BaseViewModel(), SearchClicksListener, ChipGroupClickListener {

    val searchQuery = MutableLiveData<String>()

    private val _searchResultState = MutableLiveData<UiState<List<SearchResult>>>(UiState.Loading)
    val searchResultState: LiveData<UiState<List<SearchResult>>> = _searchResultState

    private val _filteredSearchResults = MutableLiveData<List<SearchResult>>(emptyList())
    val filteredSearchResults: LiveData<List<SearchResult>> = _filteredSearchResults

    var mediaType = MediaType.MOVIE

    private val searchResultItems = MutableLiveData<List<SearchResult>>(emptyList())
    private val _navigateToDetails = MutableLiveData<Event<Int>>()

    val navigateToDetails: LiveData<Event<Int>> = _navigateToDetails
    private val _navigateBack = MutableLiveData<Event<Boolean>>()

    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack


    fun getSearchMultiMedia(searchQuery: String) {

        viewModelScope.launch {
            try {
                val searchResults = searchMediaUseCase(searchQuery)
                searchResultItems.postValue(searchResults)

                _filteredSearchResults.postValue(
                    searchResultItems.value?.let {
                        filterSearchUseCase(searchResults, mediaType)
                    }
                )
            } catch (e: Exception) {
                _filteredSearchResults.postValue(emptyList())
            }
        }
    }

    override fun onChipSelected(selectedChipPosition: Int) {
        when (selectedChipPosition) {
            0 -> mediaType = MediaType.MOVIE
            1 -> mediaType = MediaType.TV
            2 -> mediaType = MediaType.PERSON
        }

        if (searchQuery.value.isNullOrEmpty()) {
            _filteredSearchResults.postValue(emptyList())
        } else {
            filterSearchResults()
        }

    }

    private fun filterSearchResults() {
        _filteredSearchResults.postValue(
            searchResultItems.value?.let { searchResults ->
                filterSearchUseCase(searchResults, mediaType)
            }
        )
    }


    override fun onItemClick(searchId: Int) {
        _navigateToDetails.postEvent(searchId)
    }


    fun onBackClick() {
        _navigateBack.postEvent(true)
    }
}
