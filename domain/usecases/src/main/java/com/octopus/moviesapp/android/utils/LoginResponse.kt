package com.octopus.moviesapp.android.usecases

sealed class LoginResponse {
    object Success : LoginResponse()
    data class Failure(val message: String) : LoginResponse()
}