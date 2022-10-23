package com.octopus.moviesapp.ui.settings

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.injection.app.Application
import com.octopus.moviesapp.data.local.datastore.DataStorePref
import com.octopus.moviesapp.domain.types.Language
import com.octopus.moviesapp.domain.types.Theme
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.SettingsService
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val dataStorePreferences: DataStorePref
    ): ViewModel() {

    private val _languageChoiceClicked = MutableLiveData(Event(false))
    val languageChoiceClicked: LiveData<Event<Boolean>> get() = _languageChoiceClicked

    private val _currentLanguage = MutableLiveData<Language>()
    val currentLanguage: LiveData<Language> get() = _currentLanguage

    private val _themeChoiceClicked = MutableLiveData(Event(false))
    val themeChoiceClicked: LiveData<Event<Boolean>> get() = _themeChoiceClicked

    private val _currentTheme = MutableLiveData<Theme>()
    val currentLTheme: LiveData<Theme> get() = _currentTheme

    private val _navigateToAbout = MutableLiveData<Event<Boolean>>()
    val navigateToAbout: LiveData<Event<Boolean>> get() = _navigateToAbout

    private val settingsService = SettingsService

    val sessionId = Application.sessionId
    val isLoggedIn = sessionId.isNotEmpty()

    private val _profileState = MutableLiveData(UiState.Loading)

    init {
        _currentLanguage.postValue(settingsService.getCurrentLanguage())
        _currentTheme.postValue(settingsService.getCurrentAppTheme(context))
    }

    fun onLanguageChoiceClick() {
        _languageChoiceClicked.postEvent(true)
    }

    fun onThemeChoiceClick() {
        _themeChoiceClicked.postEvent(true)
    }

    fun handleLanguageChange(newLanguage: Language) {
        settingsService.updateAppLanguage(newLanguage)
        _currentLanguage.postValue(newLanguage)
    }

    fun handleThemeChange(newTheme: Theme) {
        settingsService.updateAppTheme(newTheme)
        _currentTheme.postValue(newTheme)
        viewModelScope.launch {
            dataStorePreferences.writeString(Constants.DARK_MODE,newTheme.name)
        }
    }

    fun onAboutClick() {
        _navigateToAbout.postEvent(true)
    }

    fun onLogoutClick() {

    }

}