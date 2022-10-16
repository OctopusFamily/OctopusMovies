package com.octopus.moviesapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
): ViewModel() {

    private val _navigateSearch = MutableLiveData<Event<Boolean>>()
    val navigateSearch: LiveData<Event<Boolean>> get() = _navigateSearch

    fun onClickSearch(){
        _navigateSearch.postEvent(true)
    }
}