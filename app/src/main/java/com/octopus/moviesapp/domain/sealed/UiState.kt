package com.octopus.moviesapp.domain.sealed

sealed class UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String = "There is no internet connection") : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}