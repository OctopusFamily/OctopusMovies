package com.octopus.moviesapp.android.utils

sealed class LoginResponse {
    object Success : LoginResponse()
    data class Failure(val message: String) : LoginResponse()
}