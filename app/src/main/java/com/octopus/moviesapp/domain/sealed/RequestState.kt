package com.octopus.moviesapp.domain.sealed

sealed class RequestState<out T> {
    data class Success<T>(val data: T?) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
}