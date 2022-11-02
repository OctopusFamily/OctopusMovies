package com.octopus.moviesapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.domain.types.MediaType
import com.octopus.moviesapp.domain.use_case.search.FilterSearchResultsUseCase
import com.octopus.moviesapp.domain.use_case.search.SearchMediaUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.search.mapper.asSearchResultUiState
import com.octopus.moviesapp.ui.search.uistate.SearchMainUiState
import com.octopus.moviesapp.ui.search.uistate.SearchResultUiState
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMediaUseCase: SearchMediaUseCase,
    private val filterSearchUseCase: FilterSearchResultsUseCase,
) : BaseViewModel(), SearchClicksListener, ChipGroupClickListener {

    private val _searchResultState = MutableStateFlow(SearchMainUiState())
    val searchResultState: StateFlow<SearchMainUiState> = _searchResultState

    private var allMediaSearchResults = listOf<SearchResult>()


    private val _navigateToDetails = MutableLiveData<Event<Int>>()

    val navigateToDetails: LiveData<Event<Int>> = _navigateToDetails
    private val _navigateBack = MutableLiveData<Event<Boolean>>()

    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack


    private fun getSearchMultiMedia(searchQuery: String) {

        viewModelScope.launch {
            try {
                val searchResults = searchMediaUseCase(searchQuery)
                allMediaSearchResults = searchResults

                _searchResultState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        isError = false,
                        searchResults = filterSearchResults(searchResults),
                    )
                }
            } catch (e: Exception) {
                _searchResultState.update {
                    it.copy(
                        isError = true,
                        isSuccess = false,
                        isLoading = false,
                        searchResults = emptyList(),
                    )
                }
            }
        }
    }

    override fun onChipSelected(selectedChipPosition: Int) {

        when (selectedChipPosition) {
            0 -> _searchResultState.update { it.copy(mediaType = MediaType.MOVIE) }
            1 -> _searchResultState.update { it.copy(mediaType = MediaType.TV) }
            2 -> _searchResultState.update { it.copy(mediaType = MediaType.PERSON) }
        }

        if (searchResultState.value.query.isEmpty()) {
            _searchResultState.update {
                (it.copy(
                    searchResults = emptyList(),
                    isSuccess = true,
                    isError = false,
                    isLoading = false
                ))
            }
        } else {

            _searchResultState.update {
                it.copy(
                    isSuccess = true,
                    isLoading = false,
                    isError = false,
                    searchResults = filterSearchResults(allMediaSearchResults)
                )
            }
        }
    }


    private fun filterSearchResults(searchResults: List<SearchResult>): List<SearchResultUiState> {
        return filterSearchUseCase(
            searchResults,
            _searchResultState.value.mediaType
        ).map { searchResult ->
            searchResult.asSearchResultUiState()
        }
    }

    override fun onItemClick(searchId: Int) {
        _navigateToDetails.postEvent(searchId)
    }

    fun onBackClick() {
        _navigateBack.postEvent(true)
    }

    fun onTextChanged(text: String) {
        _searchResultState.update { it.copy(query = text) }
        getSearchMultiMedia(_searchResultState.value.query)
    }
}

