package com.octopus.moviesapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkState(context: Context) : ConnectivityManager.NetworkCallback(){

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    private val _state: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val state: MutableLiveData<Boolean> get() = _state

    init {
        listenerNetworkState()
    }

    private fun listenerNetworkState() {

        Log.v("testss", "NetworkStateImpl listenerNetworkState ")

        connectivityManager.registerDefaultNetworkCallback(this@NetworkState)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)

        Log.v("testss", "NetworkStateImpl onAvailable ")
        _state.postValue(true)
    }

    private fun noConnection() {
        _state.postValue(false)
    }

    override fun onUnavailable() {
        super.onUnavailable()
        Log.v("testss", "NetworkStateImpl onUnavailable ")
        noConnection()
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        Log.v("testss", "NetworkStateImpl onLosing ")
        noConnection()
    }


    override fun onLost(network: Network) {
        super.onLost(network)
        Log.v("testss", "NetworkStateImpl onLost ")
        noConnection()
    }

}
