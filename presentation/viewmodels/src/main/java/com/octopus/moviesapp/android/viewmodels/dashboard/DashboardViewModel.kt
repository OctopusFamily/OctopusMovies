package com.octopus.moviesapp.android.viewmodels.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.octopus.moviesapp.android.usecases.UpdateThemeUseCase
import com.octopus.moviesapp.android.usecases.account.GetAccountUseCase
import com.octopus.moviesapp.android.usecases.account.LogoutUserUseCase
import com.octopus.moviesapp.android.viewmodels.dashboard.uistate.DashboardMainUiState
import com.octopus.moviesapp.android.viewmodels.dashboard.uistate.ProfileUiState
import com.octopus.moviesapp.models.model.Account
import com.octopus.moviesapp.models.type.Language
import com.octopus.moviesapp.models.type.Theme
import com.octopus.moviesapp.models.util.SettingsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val getAccountUseCase: GetAccountUseCase,
    private val updateThemeUseCase: UpdateThemeUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
) : BaseViewModel() {

    private val _dashboardMainUiState = MutableStateFlow(DashboardMainUiState())
    val dashboardMainUiState: StateFlow<DashboardMainUiState> get() = _dashboardMainUiState

    private val _isLoginClicked = MutableLiveData<Event<Boolean>>()
    val loginClicked: LiveData<Event<Boolean>> get() = _isLoginClicked

    private val _isLogOutClicked = MutableLiveData<Event<Boolean>>()
    val logOutClicked: LiveData<Event<Boolean>> get() = _isLogOutClicked

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
    val navigateToMyLists: LiveData<Event<Boolean>> get() = _navigateToMyLists

    private val settingsService = SettingsService

    init {
        checkIfUserLoggedIn()
        _currentLanguage.postValue(settingsService.getCurrentLanguage())
        _currentTheme.postValue(settingsService.getCurrentAppTheme(context))
    }

    private fun checkIfUserLoggedIn() {
        val sessionId = MyApplication.sessionId
        if (sessionId.isNotEmpty()) {
            _dashboardMainUiState.update {
                it.copy(
                    isLoggedIn = true,
                    isProfileSuccess = true,
                    isProfileError = false,
                    isProfileLoading = false
                )
            }
            getProfileDetails(sessionId)
        }
    }

    private fun getProfileDetails(sessionId: String) {
        //  _dashboardMainUiState.update { it.copy(isProfileLoading = true) }
        viewModelScope.launch {
            try {
                val account = getAccountUseCase(sessionId)
                _dashboardMainUiState.update {
                    it.copy(
                        isLoggedIn = true,
                        isProfileError = false,
                        isProfileSuccess = true,
                        isProfileLoading = false,
                        profileUiState = account.asProfileUiState()
                    )
                }
            } catch (e: Exception) {
                _dashboardMainUiState.update {
                    it.copy(
                        isProfileLoading = false,
                        isProfileError = true,
                        isLoggedIn = false,
                        isProfileSuccess = false,
                    )
                }
            }
        }
    }

    fun tryToGetProfileDetailsAgain() {
        getProfileDetails(MyApplication.sessionId)
    }

    fun onLanguageChoiceClick() {
        _languageChoiceClicked.postEvent(true)
    }

    fun onThemeChoiceClick() {
        _themeChoiceClicked.postEvent(true)
    }

    fun onMyListsClick() {
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
            updateThemeUseCase(newTheme)
        }
    }

    fun onAboutClick() {
        _navigateToAbout.postEvent(true)
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            try {
                val logOut = logoutUserUseCase(MyApplication.sessionId)
                if (logOut.loggedOut) {
                    logoutUserUseCase.removeUserFromDataStore()
                    _isLogOutClicked.postEvent(true)
                    _dashboardMainUiState.update {
                        it.copy(
                            isLoggedIn = false,
                            isProfileSuccess = false,
                            isProfileError = false,
                            isProfileLoading = false,
                        )
                    }
                }
            } catch (e: Exception) {
                // Implement this later!
            }
        }
    }

    fun onLogInClicked() {
        _isLoginClicked.postEvent(true)
    }

    private fun Account.asProfileUiState(): ProfileUiState {
        return ProfileUiState(
            name = name,
            username = username,
            profileImageUrl = avatarPath
        )
    }
}