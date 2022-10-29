package com.octopus.moviesapp.domain.login

sealed class LoginResponse {
    object Success : LoginResponse()
    data class Failure(val message: String) : LoginResponse()
}