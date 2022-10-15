package com.octopus.moviesapp.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.enums.Language
import com.octopus.moviesapp.domain.enums.Theme
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.SettingsService
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(): ViewModel() {

    private val _languageChoiceClicked = MutableLiveData(Event(false))
    val languageChoiceClicked: LiveData<Event<Boolean>> get() = _languageChoiceClicked

    private val _newLanguageChose = MutableLiveData(Event(false))
    val newLanguageChose: LiveData<Event<Boolean>> get() = _newLanguageChose

    private val _currentLanguage = MutableLiveData<Language>()
    val currentLanguage: LiveData<Language> get() = _currentLanguage

    private val _themeChoiceClicked = MutableLiveData(Event(false))
    val themeChoiceClicked: LiveData<Event<Boolean>> get() = _themeChoiceClicked

    private val _newThemeChose = MutableLiveData(Event(false))
    val newThemeChose: LiveData<Event<Boolean>> get() = _newThemeChose

    private val _currentTheme = MutableLiveData<Theme>()
    val currentLTheme: LiveData<Theme> get() = _currentTheme

    init {
        _currentLanguage.postValue(SettingsService.currentLanguage)
        _currentTheme.postValue(SettingsService.currentTheme)
    }

    fun onLanguageChoiceClick() {
        _languageChoiceClicked.postEvent(true)
    }

    fun onThemeChoiceClick() {
        _themeChoiceClicked.postEvent(true)
    }

    fun handleLanguageChange(newLanguage: Language) {
        viewModelScope.launch {
            _newLanguageChose.postEvent(true)
            _currentLanguage.postValue(newLanguage)
        }
    }

    fun handleThemeChange(newTheme: Theme) {
        viewModelScope.launch {
            _newThemeChose.postEvent(true)
            _currentTheme.postValue(newTheme)
        }
    }

}