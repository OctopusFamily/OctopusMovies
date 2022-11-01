package com.octopus.moviesapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.login.LoginResponse
import com.octopus.moviesapp.domain.login.LoginUseCase
import com.octopus.moviesapp.data.local.datastore.DataStorePreferences
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.*
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val authUtils: AuthUtilsImpl,
    private val dataStorePreferences: DataStorePreferences,
    saveStateHandle: SavedStateHandle
) : BaseViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val args = LoginStateDialogFragmentArgs.fromSavedStateHandle(saveStateHandle)

    private val _usernameError = MutableLiveData(Constants.EMPTY_TEXT)
    val usernameError: LiveData<String> get() = _usernameError

    private val _passwordError = MutableLiveData(Constants.EMPTY_TEXT)
    val passwordError: LiveData<String> get() = _passwordError

    private val _loginMainUiState = MutableStateFlow(LoginMainUiState())
    val loginMainUiState = _loginMainUiState

    private val _signUpEvent = MutableLiveData(Event(false))
    val signUpEvent: LiveData<Event<Boolean>> get() = _signUpEvent

    private val _isPasswordShown = MutableLiveData(false)
    val isPasswordShown: LiveData<Boolean> get() = _isPasswordShown

    private val _skipLoginClicked = MutableLiveData(Event(false))
    val skipLoginClicked = _skipLoginClicked

    private val _forgotPasswordClicked = MutableLiveData(Event(false))
    val forgotPasswordClicked = _forgotPasswordClicked

    private val _isDialogShown = MutableLiveData(false)
    val isDialogShown: LiveData<Boolean> get() = _isDialogShown

    private val _loginEvent = MutableLiveData<Event<Boolean>>()
    val loginEvent  = _loginEvent


    fun onSignUpClick() {
        _signUpEvent.postValue(Event(true))
    }

    fun onLoginClick() {
        if (checkUserInputs()) _isDialogShown.postValue(true)
        else checkUserInputs()
    }

    fun onForgotPasswordClick() {
        _forgotPasswordClicked.postValue(Event(true))
    }

    fun onClickSkip() {
        viewModelScope.launch {
            dataStorePreferences.writeString(Constants.SESSION_ID_KEY, "")
            _skipLoginClicked.postValue(Event(true))
        }
    }

    fun onClickShowPassWord() {
        if (_isPasswordShown.value == false) {
            _isPasswordShown.postValue(true)
        } else {
            _isPasswordShown.postValue(false)
        }
    }

    fun login() {
        viewModelScope.launch {
            try {
                _loginMainUiState.update{it.copy(isLoading = true)}
                val response = loginUseCase(username = args.username, password = args.password)
                if (response is LoginResponse.Success){
                    onLoginSuccessfully()
                }
                else if (response is LoginResponse.Failure){
                    onLoginError(response.message)
                }

            } catch (e : Throwable){
                onLoginError(e.message.toString())

            }
        }
    }

    private fun onLoginSuccessfully() {
        _loginMainUiState.update { it.copy(isSuccess = true) }
        _loginEvent.postEvent(true)
    }

    private fun onLoginError(message : String) {
        _loginMainUiState.update { it.copy(isError = true, error = message) }
        _passwordError.postValue(message)
        _loginEvent.postEvent(false)

    }

    private fun checkUserInputs(): Boolean {
        val isUsernameValid = checkUsernameValidation()
        val isPasswordValid = checkPasswordValidation()
        return isPasswordValid && isUsernameValid
    }

    private fun checkUsernameValidation(): Boolean {
        return authUtils.validateUsername(password.value).grabError()?.let { error ->
            _usernameError.postValue(error)
            false
        } ?: true.also { _usernameError.postValue(Constants.EMPTY_TEXT) }
    }

    private fun checkPasswordValidation(): Boolean {
        return authUtils.validatePassword(password.value).grabError()?.let { error ->
            _passwordError.postValue(error)
            false
        } ?: true.also { _passwordError.postValue(Constants.EMPTY_TEXT) }
    }

}
