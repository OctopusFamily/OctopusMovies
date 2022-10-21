package com.octopus.moviesapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.AccountRepository
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val authUtils: AuthUtilsImpl,
    saveStateHandle: SavedStateHandle
) : BaseViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val args = LoginStateDialogFragmentArgs.fromSavedStateHandle(saveStateHandle)

    private val _usernameError = MutableLiveData(Constants.EMPTY_TEXT)
    val usernameError: LiveData<String> get() = _usernameError

    private val _passwordError = MutableLiveData(Constants.EMPTY_TEXT)
    val passwordError: LiveData<String> get() = _passwordError

    private val _loginRequestState = MutableLiveData<UiState<Boolean>>()
    val loginRequestState = _loginRequestState

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
        _skipLoginClicked.postValue(Event(true))
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
            accountRepository.login(username = args.username, password = args.password).collect {
                when (it) {
                    is UiState.Error -> onLoginError(it.message)
                    is UiState.Success -> onLoginSuccessfully()
                    UiState.Loading -> _loginRequestState.postValue(it)
                }
            }
        }
    }

    private fun onLoginSuccessfully() {
        _loginRequestState.postValue(UiState.Success(true))
    }

    private fun onLoginError(message: String) {
        _loginRequestState.postValue(UiState.Error(message))
        _passwordError.postValue(message)
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
