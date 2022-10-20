package com.octopus.moviesapp.util

sealed class InputValidationResult {
    object Valid : InputValidationResult()
    data class NotValid(val error: String) : InputValidationResult()
    fun grabError() = if (this is NotValid) error else null
}