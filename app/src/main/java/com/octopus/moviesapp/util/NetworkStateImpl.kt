package com.octopus.moviesapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkStateImpl(context: Context) : NetworkState() {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var _state: Flow<Boolean> = flow {
        emit(false)
    }

    override fun state(): Flow<Boolean> {
        listenerNetworkState()
        return _state
    }

    private fun listenerNetworkState() {
        connectivityManager.registerDefaultNetworkCallback(this@NetworkStateImpl)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        _state = flow {
            emit(true)
        }
    }

    private fun noConnection() {
        _state = flow {
            emit(false)
        }
    }

    override fun onUnavailable() {
        super.onUnavailable()
        noConnection()
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        noConnection()
    }


    override fun onLost(network: Network) {
        super.onLost(network)
        noConnection()
    }

}
