package com.octopus.moviesapp.ui.login

data class LoginUiState(
    val isLoading:Boolean = false,
    val isSuccess:Boolean = false,
    val isError:Boolean = false,
    val userName:String = "",
    val passWord:String = "",
    val error:String = "",
)