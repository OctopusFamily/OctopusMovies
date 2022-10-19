package com.octopus.moviesapp.util

interface ConnectionStatus {
    suspend fun isInternetConnectionAvailable(): Boolean
}