package com.octopus.moviesapp.ui.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.repository.MainRepository
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel(), TVShowsClicksListener {

    override fun onTVShowClick(tvShowId: Int) {

    }

    private val _tvShowsListState = MutableLiveData<UiState<List<TVShow>>>(UiState.Loading)
    val tvShowsListState: LiveData<UiState<List<TVShow>>> get() = _tvShowsListState

    private var currentTVShowsCategory = TVShowsCategory.POPULAR

    private fun getTVShowsByCategory(type: TVShowsCategory) {
        viewModelScope.launch {
            wrapResponse { repository.getTVShowByCategory(type,1) }.collectLatest {
                _tvShowsListState.postValue(it)
            }
        }
    }
}