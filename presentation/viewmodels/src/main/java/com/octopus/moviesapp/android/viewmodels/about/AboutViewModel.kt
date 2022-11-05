package com.octopus.moviesapp.android.viewmodels.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor() : ViewModel() {

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _openGithubLinkInBrowser = MutableLiveData<Event<Boolean>>()
    val openGithubLinkInBrowser: LiveData<Event<Boolean>> get() = _openGithubLinkInBrowser

    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    fun onGithubLinkClick() {
        _openGithubLinkInBrowser.postEvent(true)
    }

}