package com.octopus.moviesapp.android.viewmodels.login

data class LoginMainUiState(
    val isLoading:Boolean = false,
    val isSuccess:Boolean = false,
    val isError:Boolean = false,
    val error:String = "",
)