package com.octopus.moviesapp.util

interface AuthUtils {
    fun validateUsername(username: String?): InputValidationResult
    fun validatePassword(password: String?): InputValidationResult
}