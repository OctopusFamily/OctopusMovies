package com.octopus.moviesapp.util

interface ConnectionTracker {
    suspend fun isInternetConnectionAvailable(): Boolean
}