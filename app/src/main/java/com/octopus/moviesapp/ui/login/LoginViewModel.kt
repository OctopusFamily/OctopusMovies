package com.octopus.moviesapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.AccountRepository
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.AuthUtilsImpl
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val textValidation: AuthUtilsImpl
) : BaseViewModel() {

    val userName = MutableLiveData<String?>()
    val password = MutableLiveData<String?>()

    private val _userNameHelperText = MutableLiveData("")
    val userNameHelperText = _userNameHelperText

    private val _passwordHelperText = MutableLiveData("")
    val passwordHelperText = _passwordHelperText

    private val _loginRequestState = MutableLiveData<UiState<Boolean>>()
    val loginRequestState = _loginRequestState

    private val _loginEvent = MutableLiveData<Event<Boolean>>()
    val loginEvent = _loginEvent

    private val _signUpEvent = MutableLiveData(Event(false))
    val signUpEvent: LiveData<Event<Boolean>>
        get() = _signUpEvent

    private val _isShowPassWordEnabled = MutableLiveData(false)
    val isShowPassWordEnabled: LiveData<Boolean>
        get() = _isShowPassWordEnabled

    private val _isSkip = MutableLiveData(Event(false))
    val isSkip = _isSkip

    fun onClickSignUp() {
        _signUpEvent.postValue(Event(true))
    }

    fun onClickLogin() {
        if (checkFormValidation())  login()
        else checkFormValidation()
    }

    fun onClickSkip(){
        _isSkip.postValue(Event(true))
    }

    fun onClickShowPassWord() {
        if (_isShowPassWordEnabled.value == false) {
            _isShowPassWordEnabled.postValue(true)
        } else {
            _isShowPassWordEnabled.postValue(false)
        }
    }

    private fun login() {
        viewModelScope.launch {
            accountRepository.login(
                userName.value.toString(),
                password.value.toString()
            ).collect {
                when (it) {
                    is UiState.Error -> onLoginError(it.message)
                    UiState.Loading -> _loginRequestState.postValue(it)
                    is UiState.Success -> onLoginSuccessfully()
                }
            }
        }
    }

    private fun onLoginSuccessfully() {
        _loginRequestState.postValue(UiState.Success(true))
        _loginEvent.postEvent(true)

    }

    private fun onLoginError(message: String) {
        _loginRequestState.postValue(UiState.Error(message))
        _passwordHelperText.postValue(message)

    }

    private fun checkPasswordValidation(): Boolean {
        val passwordFieldState = textValidation.validatePassword(password.value)

        return if (passwordFieldState == null) {
            _passwordHelperText.postValue("")
            true
        } else {
            _passwordHelperText.postValue(passwordFieldState.toString())
            false
        }

    }

    private fun checkUserNameValidation(): Boolean {
        val userNameFieldState = textValidation.validateUsername(userName.value.toString())
        return if (userNameFieldState == null) {
            _userNameHelperText.postValue("")
            true
        } else {
            _userNameHelperText.postValue(userNameFieldState.toString())
            false
        }
    }

    private fun checkFormValidation(): Boolean {
        val isUserNameValid = checkUserNameValidation()
        val isPasswordValid = checkPasswordValidation()

        return isPasswordValid && isUserNameValid
    }

}
