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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryMovie: MoviesRepository,
) : BaseViewModel(), SearchClicksListener, ChipGroupClickListener {

    val searchQuery = MutableLiveData<String>()

    private val _searchResult = MutableLiveData<UiState<List<SearchResult>?>?>(UiState.Loading)
    val searchResult: LiveData<UiState<List<SearchResult>?>?> = _searchResult

    private val _filteredSearchResults = MutableLiveData<List<SearchResult>>(emptyList())
    val filteredSearchResults: LiveData<List<SearchResult>> = _filteredSearchResults

    private val _searchType = MutableLiveData(SearchType.MOVIE.pathName)
    val searchType: LiveData<String> = _searchType

    private val searchResultItems = MutableLiveData<List<SearchResult>?>()

    private val _navigateToDetails = MutableLiveData<Event<Int>>()
    val navigateToDetails: LiveData<Event<Int>> = _navigateToDetails

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack


    fun getSearchMultiMedia(searchQuery: String) {
        if (searchQuery.isNotEmpty()) {
            viewModelScope.launch {
                wrapResponse {
                    repositoryMovie.getSearchMultiMedia(searchQuery)
                }.collectLatest { searchState ->
                    if (searchState is UiState.Success) {
                        filterSearchResultsByType(searchState.data, _searchType.value.toString())
                        searchResultItems.postValue(searchState.data)
                        _searchResult.postValue(searchState)
                    }
                }
            }

        }else  filterSearchResultsByType(emptyList(),_searchType.value.toString())

    }

    private fun filterSearchResultsByType(data: List<SearchResult>, type: String) {
        _filteredSearchResults.postValue(data.filter { it.mediaType == type })
    }

    override fun onChipSelected(selectedItemId: Int) {
        if (searchQuery.value?.isNotBlank() == true) {
            when (selectedItemId) {
                0 -> _searchType.value = SearchType.MOVIE.pathName
                1 -> _searchType.value = SearchType.TV.pathName
                2 -> _searchType.value = SearchType.PERSON.pathName
            }
            searchResultItems.value?.let {
                filterSearchResultsByType(it, _searchType.value.toString())
            }
        } else return filterSearchResultsByType(emptyList(), _searchType.value.toString())

    }


    fun tryLoadDataAgain(searchQuery: String) {
        getSearchMultiMedia(searchQuery)
    }

    fun onBackClick() {
        _navigateBack.postEvent(true)
    }

    override fun onItemClick(searchId: Int) {
        _navigateToDetails.postEvent(searchId)
    }
}

