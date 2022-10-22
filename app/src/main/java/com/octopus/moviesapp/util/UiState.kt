package com.octopus.moviesapp.util

sealed class UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()

    fun toData() = if (this is Success) data else null
}