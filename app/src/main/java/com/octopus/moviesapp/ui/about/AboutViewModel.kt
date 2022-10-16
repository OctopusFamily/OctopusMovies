package com.octopus.moviesapp.ui.about

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.types.Language
import com.octopus.moviesapp.domain.types.Theme
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.SettingsService
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>> get() = _navigateBack

    private val _navigateGithubLink = MutableLiveData<Event<Boolean>>()
    val navigateGithubLink: LiveData<Event<Boolean>> get() = _navigateGithubLink

    fun onNavigateBackClick() {
        _navigateBack.postEvent(true)
    }

    fun onNavigateGithubClick() {
        _navigateGithubLink.postEvent(true)

    }

}