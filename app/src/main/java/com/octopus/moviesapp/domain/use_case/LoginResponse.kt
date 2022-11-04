package com.octopus.moviesapp.domain.use_case

sealed class LoginResponse {
    object Success : LoginResponse()
    data class Failure(val message: String) : LoginResponse()
}