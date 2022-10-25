package com.octopus.moviesapp.ui.settings

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.MyApplication
import com.octopus.moviesapp.data.local.DataStorePref
import com.octopus.moviesapp.data.repository.account.AccountRepository
import com.octopus.moviesapp.domain.model.Account
import com.octopus.moviesapp.domain.types.Language
import com.octopus.moviesapp.domain.types.Theme
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.SettingsService
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val dataStorePreferences: DataStorePref,
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    private val _profileDetails = MutableLiveData<UiState<Account>>(UiState.Loading)
    val profileDetails = _profileDetails

    private val _logOutClicked = MutableLiveData(Event(false))
    val logOutClicked = _logOutClicked

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

    private val _navigateToMyLists = MutableLiveData<Event<Boolean>>()
    val navigateToMyLists : LiveData<Event<Boolean>> get() = _navigateToMyLists

    private val settingsService = SettingsService

    val sessionId = MyApplication.sessionId
    val isLoggedIn = sessionId.isNotEmpty()

    private val _profileState = MutableLiveData(UiState.Loading)

    init {
        getProfileDetails()
        _currentLanguage.postValue(settingsService.getCurrentLanguage())
        _currentTheme.postValue(settingsService.getCurrentAppTheme(context))
    }

    private fun getProfileDetails() {
        viewModelScope.launch {
            wrapResponse {
                accountRepository.getAccountDetails(sessionId)
            }.collectLatest {
                _profileDetails.postValue(it)
            }
        }
    }

    fun onLanguageChoiceClick() {
        _languageChoiceClicked.postEvent(true)
    }

    fun onThemeChoiceClick() {
        _themeChoiceClicked.postEvent(true)
    }

    fun onMyListsClick(){
        _navigateToMyLists.postEvent(true)
    }

    fun handleLanguageChange(newLanguage: Language) {
        settingsService.updateAppLanguage(newLanguage)
        _currentLanguage.postValue(newLanguage)
    }

    fun handleThemeChange(newTheme: Theme) {
        settingsService.updateAppTheme(newTheme)
        _currentTheme.postValue(newTheme)
        viewModelScope.launch {
            dataStorePreferences.writeString(Constants.DARK_MODE, newTheme.name)
        }
    }

    fun onAboutClick() {
        _navigateToAbout.postEvent(true)
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            accountRepository.logout().collect {
                if (it is UiState.Success) {
                    _logOutClicked.postEvent(true)
                }
            }
        }
    }
}