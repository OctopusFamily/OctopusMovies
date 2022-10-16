package com.octopus.moviesapp.util

import android.net.ConnectivityManager
import kotlinx.coroutines.flow.Flow

interface Network

abstract class NetworkState : Network, ConnectivityManager.NetworkCallback() {
    abstract fun state(): Flow<Boolean>

}




