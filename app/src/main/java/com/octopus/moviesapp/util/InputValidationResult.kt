package com.octopus.moviesapp.util

sealed class InputValidationResult {
    object Valid : InputValidationResult()
    data class InValid(val error: String) : InputValidationResult()
    fun grabError() = if (this is InValid) error else null
}