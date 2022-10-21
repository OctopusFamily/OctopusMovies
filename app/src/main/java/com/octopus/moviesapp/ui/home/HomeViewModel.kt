package com.octopus.moviesapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.home.HomeRepository
import com.octopus.moviesapp.domain.types.MediaType
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
): BaseViewModel() {

    init {
        getTrending(MediaType.MOVIE)
    }

    private val _navigateSearch = MutableLiveData<Event<Boolean>>()
    val navigateSearch: LiveData<Event<Boolean>> get() = _navigateSearch

    fun onClickSearch(){
        _navigateSearch.postEvent(true)
    }

    private fun getTrending(mediaType: MediaType) {
        viewModelScope.launch {
            wrapResponse { homeRepository.getTrendingMedia(mediaType) }.collectLatest {
                Log.d("MALT", "STATE IS: $it")
            }
        }
    }
}